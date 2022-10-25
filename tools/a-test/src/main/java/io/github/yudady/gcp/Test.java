package io.github.yudady.gcp;

import java.nio.file.Files;
import java.nio.file.Path;


import static java.nio.charset.StandardCharsets.UTF_8;

public class Test {
    public static void testUpload() {
        String fileName = "tommy-test-file0004.png";
        System.out.println("1 ===========================================");
        try {
            String projectId = "unistar-dev-v1";
            String kekUri = "projects/unistar-dev-v1/locations/global/keyRings/service-data-keyring-v2/cryptoKeys/payment-secret-v2";
            String bucket = "ubpay-dev-v2-app-bucket";
            StorageHelper helper = new StorageHelper(projectId, kekUri, bucket);
            System.out.println("2 ===========================================");
            byte[] input = Files.readAllBytes(Path.of("C:\\Users\\tommy.lin\\Pictures\\google.png"));
            System.out.println("3 ===========================================");
            helper.upload(fileName, input);
            System.out.println("4 ===========================================");
            byte[] image = helper.download(fileName);
            System.out.println("5 ===========================================");
            Files.write(Path.of("C:\\Users\\tommy.lin\\Pictures\\" + fileName), image);
            System.out.println("6 ===========================================");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error creating primitive: %s " + ex);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        testUpload();
        //        String kekUri = "projects/unistar-dev-v1/locations/global/keyRings/service-data-keyring-v2/cryptoKeys/payment-secret-v2";
        //        CryptoHelper helper = new CryptoHelper(kekUri);
        //        test(helper, "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十");
        //        test(helper, "測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試");
        //        error(helper, ";sadadsa".getBytes(UTF_8));
    }

    private static void test(CryptoHelper helper, String plaintext) {
        System.out.println("----------------------------");
        System.out.println("original ->" + plaintext);
        System.out.println("original.getByte.length ->" + plaintext.getBytes(UTF_8).length);
        System.out.println("original.length ->" + plaintext.length());
        String ciphertextBase64 = helper.encryptBase64(plaintext);
        System.out.println(ciphertextBase64);
        System.out.println(ciphertextBase64.length());
        System.out.println("original from decrypt ->" + helper.decryptBase64(ciphertextBase64));
    }

    private static void error(CryptoHelper helper, byte[] plaintext) {
        System.out.println("error ----------------------------");
        byte[] cipherBytes = helper.encrypt(plaintext);
        String ciphertext = new String(cipherBytes, UTF_8); // save to DB
        System.out.println("cipherBytes.length -> " + cipherBytes.length);
        System.out.println("ciphertext.getBytes.length ->" + ciphertext.getBytes(UTF_8).length);
        var plaintextByte = helper.decrypt(ciphertext.getBytes(UTF_8));
        System.out.println("original from decrypt ->" + new String(plaintextByte, UTF_8));
    }
}
