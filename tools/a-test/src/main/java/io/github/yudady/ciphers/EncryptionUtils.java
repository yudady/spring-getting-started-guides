package io.github.yudady.ciphers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

/**
 * Utility class for loading certificates and keys.
 */
public final class EncryptionUtils {

    private static final String PKCS_1_PEM_HEADER = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String PKCS_1_PEM_FOOTER = "-----END RSA PRIVATE KEY-----";
    private static final String PKCS_8_PEM_HEADER = "-----BEGIN PRIVATE KEY-----";
    private static final String PKCS_8_PEM_FOOTER = "-----END PRIVATE KEY-----";

    private EncryptionUtils() {
    }

    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = loadDecryptionKey(System.getProperty("user.dir") + "/01.unistar/work-test/src/main/resources/CNY.key");
        System.out.println("privateKey = " + privateKey);
        System.out.println("==========");
        System.out.println("==========");
        System.out.println("==========");

        RSAPrivateCrtKey privk = (RSAPrivateCrtKey) privateKey;
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey myPublicKey = keyFactory.generatePublic(publicKeySpec);
        System.out.println("myPublicKey = " + myPublicKey);
        System.out.println("==========");
        System.out.println("==========");
        System.out.println("==========");

        Certificate certificate = loadEncryptionCertificate(System.getProperty("user.dir") + "/01.unistar/work-test/src/main/resources/CNY.pem");
        System.out.println("certificate = " + certificate);
    }

    /**
     * Populate a X509 encryption certificate object with the certificate data at the given file path.
     */
    public static Certificate loadEncryptionCertificate(String certificatePath) throws CertificateException, FileNotFoundException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        return factory.generateCertificate(new FileInputStream(certificatePath));
    }

    /**
     * Load a RSA decryption key from a file (PEM or DER).
     */
    public static PrivateKey loadDecryptionKey(String keyFilePath) throws GeneralSecurityException, IOException {
        byte[] keyDataBytes = Files.readAllBytes(Paths.get(keyFilePath));
        String keyDataString = new String(keyDataBytes, StandardCharsets.UTF_8);

        if (keyDataString.contains(PKCS_1_PEM_HEADER)) {
            // OpenSSL / PKCS#1 Base64 PEM encoded file
            keyDataString = keyDataString.replace(PKCS_1_PEM_HEADER, "");
            keyDataString = keyDataString.replace(PKCS_1_PEM_FOOTER, "");
            keyDataString = keyDataString.replace("\n", "");
            keyDataString = keyDataString.replace("\r\n", "");
            return readPkcs1PrivateKey(base64Decode(keyDataString));
        }

        if (keyDataString.contains(PKCS_8_PEM_HEADER)) {
            // PKCS#8 Base64 PEM encoded file
            keyDataString = keyDataString.replace(PKCS_8_PEM_HEADER, "");
            keyDataString = keyDataString.replace(PKCS_8_PEM_FOOTER, "");
            keyDataString = keyDataString.replace("\n", "");
            keyDataString = keyDataString.replace("\r\n", "");
            return readPkcs8PrivateKey(base64Decode(keyDataString));
        }

        // We assume it's a PKCS#8 DER encoded binary file
        return readPkcs8PrivateKey(Files.readAllBytes(Paths.get(keyFilePath)));
    }


    public static byte[] base64Decode(String value) {
        return Base64.getDecoder().decode(value);
    }

    /**
     * Load a RSA decryption key out of a PKCS#12 container.
     */
    public static PrivateKey loadDecryptionKey(String pkcs12KeyFilePath,
                                               String decryptionKeyAlias,
                                               String decryptionKeyPassword) throws GeneralSecurityException, IOException {
        KeyStore pkcs12KeyStore = KeyStore.getInstance("PKCS12");
        pkcs12KeyStore.load(new FileInputStream(pkcs12KeyFilePath), decryptionKeyPassword.toCharArray());
        return (PrivateKey) pkcs12KeyStore.getKey(decryptionKeyAlias, decryptionKeyPassword.toCharArray());
    }

    /**
     * Create a PrivateKey instance from raw PKCS#8 bytes.
     */
    private static PrivateKey readPkcs8PrivateKey(byte[] pkcs8Bytes) throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8Bytes);
        try {
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException("Unexpected key format!", e);
        }
    }

    /**
     * Create a PrivateKey instance from raw PKCS#1 bytes.
     */
    private static PrivateKey readPkcs1PrivateKey(byte[] pkcs1Bytes) throws GeneralSecurityException {
        // We can't use Java internal APIs to parse ASN.1 structures, so we build a PKCS#8 key Java can understand
        int pkcs1Length = pkcs1Bytes.length;
        int totalLength = pkcs1Length + 22;
        byte[] pkcs8Header = new byte[]{
                0x30, (byte) 0x82, (byte) ((totalLength >> 8) & 0xff), (byte) (totalLength & 0xff), // Sequence + total length
                0x2, 0x1, 0x0, // Integer (0)
                0x30, 0xD, 0x6, 0x9, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0xD, 0x1, 0x1, 0x1, 0x5, 0x0, // Sequence: 1.2.840.113549.1.1.1, NULL
                0x4, (byte) 0x82, (byte) ((pkcs1Length >> 8) & 0xff), (byte) (pkcs1Length & 0xff) // Octet string + length
        };
        byte[] pkcs8bytes = join(pkcs8Header, pkcs1Bytes);
        return readPkcs8PrivateKey(pkcs8bytes);
    }

    public static String sanitizeJson(String json) {
        return json.replace("\n", "")
                .replace("\r", "")
                .replace("\t", "");
    }

    private static byte[] join(byte[] byteArray1, byte[] byteArray2) {
        byte[] bytes = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, bytes, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, bytes, byteArray1.length, byteArray2.length);
        return bytes;
    }
}