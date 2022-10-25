package io.github.yudady.gcp;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager;
import com.google.crypto.tink.integration.gcpkms.GcpKmsClient;


import io.github.yudady.util.Strings;
import java.security.GeneralSecurityException;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class StorageHelper {

    private final String projectId;
    private final String bucket;
    private final Aead aead;

    public StorageHelper(String projectId, String kekUri, String bucket) {
        this.projectId = projectId;
        this.bucket = bucket;
        try {
            AeadConfig.register();
            GcpKmsClient.register(Optional.empty(), Optional.empty());
            KeysetHandle handle = KeysetHandle.generateNew(
                KmsEnvelopeAeadKeyManager.createKeyTemplate("gcp-kms://" + kekUri, KeyTemplates.get("AES256_GCM")));
            aead = handle.getPrimitive(Aead.class);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void upload(String objectName, final byte[] data) {
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        String gcsBlobPath = Strings.format("gs://{}/{}", bucket, objectName);

        // This will bind the encryption to the location of the GCS blob. That if, if you rename or
        // move the blob to a different bucket, decryption will fail.
        // See https://developers.google.com/tink/aead#associated_data.
        byte[] associatedData = gcsBlobPath.getBytes(UTF_8);
        byte[] ciphertext;
        try {
            ciphertext = aead.encrypt(data, associatedData);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        BlobId blobId = BlobId.of(bucket, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, ciphertext);
    }

    public byte[] download(final String objectName) {
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        String gcsBlobPath = Strings.format("gs://{}/{}", bucket, objectName);
        byte[] input = storage.readAllBytes(bucket, objectName);


        byte[] associatedData = gcsBlobPath.getBytes(UTF_8);
        try {
            return aead.decrypt(input, associatedData);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
