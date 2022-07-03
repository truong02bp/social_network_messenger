package com.messenger.service.impl;

import com.messenger.exceptions.BusinessException;
import com.messenger.service.MinioService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String defaultBucket;

    @Override
    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException |
                 NoSuchAlgorithmException | ServerException | XmlParserException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void upload(String folder, String name, InputStream data) {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile(name, "");
            Files.copy(data, tempFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tempFile != null) {
            try {
                UploadObjectArgs.Builder builder = UploadObjectArgs.builder().bucket(defaultBucket).contentType("image/jpeg")
                    .object(folder + name).filename(tempFile.toString());
                minioClient.uploadObject(builder.build());
            } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                     InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
                throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getLocalizedMessage()).build();
            }
        }

    }

    @Override
    public byte[] getBytes(String key) {
        try {
            GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(defaultBucket)
                .object(key).build();
            InputStream obj = minioClient.getObject(objectArgs);
            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String url) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(defaultBucket).object(url).build());
        } catch (Exception e) {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Delete object fail").build();
        }
    }
}
