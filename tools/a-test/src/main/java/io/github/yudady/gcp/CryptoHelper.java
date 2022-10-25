package io.github.yudady.gcp;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiExceptions;
import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.EncryptRequest;
import com.google.cloud.kms.v1.EncryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

import io.github.yudady.ciphers.Encodings;
import io.github.yudady.util.Maps;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

public class CryptoHelper {
    private final CryptoKeyName keyName;
    private final KeyManagementServiceClient client;

    public CryptoHelper(String kekUri) {
        keyName = CryptoKeyName.parse(kekUri);
        try {
            client = KeyManagementServiceClient.create();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public byte[] encrypt(byte[] plaintext) {
        EncryptResponse response = client.encrypt(keyName, ByteString.copyFrom(plaintext));
        return response.getCiphertext().toByteArray();
    }

    public String encryptBase64(String plaintext) {
        EncryptResponse response = client.encrypt(keyName, ByteString.copyFromUtf8(plaintext));
        return Encodings.base64(response.getCiphertext().toByteArray());
    }

    public Map<String, String> futureEncryptBase64(Map<String, String> plaintextMap) {
        Map<String, EncryptRequest> requestMap = Maps.newHashMap();
        for (Map.Entry<String, String> entry : plaintextMap.entrySet()) {
            requestMap.put(entry.getKey(), EncryptRequest.newBuilder()
                .setName(keyName == null ? null : keyName.toString())
                .setPlaintext(ByteString.copyFromUtf8(entry.getValue()))
                .build());
        }

        Map<String, ApiFuture<EncryptResponse>> futureMap = Maps.newHashMap();
        for (Map.Entry<String, EncryptRequest> entry : requestMap.entrySet()) {
            futureMap.put(entry.getKey(), client.encryptCallable().futureCall(entry.getValue()));
        }

        Map<String, String> resultMap = Maps.newHashMap();
        for (Map.Entry<String, ApiFuture<EncryptResponse>> entry : futureMap.entrySet()) {
            EncryptResponse response = ApiExceptions.callAndTranslateApiException(entry.getValue());
            resultMap.put(entry.getKey(), Encodings.base64(response.getCiphertext().toByteArray()));
        }

        return resultMap;
    }

    public byte[] decrypt(byte[] ciphertext) {
        DecryptResponse response = client.decrypt(keyName, ByteString.copyFrom(ciphertext));
        return response.getPlaintext().toByteArray();
    }

    public String decryptBase64(String ciphertextBase64) {
        DecryptResponse response = client.decrypt(keyName, ByteString.copyFrom(Encodings.decodeBase64(ciphertextBase64)));
        return response.getPlaintext().toStringUtf8();
    }
}
