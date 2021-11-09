package com.example.admin.service.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AwsFileServiceImpl implements AwsFileService {

    private final S3Client s3client;

    @Value("${aws.s3-bucket}")
    private String s3Bucket;

    @Override
    public boolean isExists(@NonNull String path) {
        try {
            s3client.headObject(builder -> builder.bucket(s3Bucket).key(path));
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        }
    }

    @Override
    public InputStream download(@NonNull String path) {
        return s3client.getObject(builder -> builder.bucket(s3Bucket).key(path));
    }

    @Override
    public void upload(@NonNull String path, @NonNull InputStream in, long size) {
        s3client.putObject(
                builder -> builder.bucket(s3Bucket).key(path),
                RequestBody.fromInputStream(in, size)
        );
    }

    @Override
    public void delete(@NonNull String path) {
        s3client.deleteObject(builder -> builder.bucket(s3Bucket).key(path));
    }

//
//    @Override
//    public Optional<InputStreamResource> download(String key) {
//        return s3client.doesObjectExist(s3Bucket, key)
//                ? Optional.of(new InputStreamResource(s3client.getObject(s3Bucket, key).getObjectContent()))
//                : Optional.empty();
//    }

//    @Override
//    public void upload(String key, InputStream inputStream, long size) throws IOException {
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key,
//                String.valueOf(RequestBody.fromInputStream(inputStream, size)));
//        s3Client.putObject(putObjectRequest);
////        inputStream.close();
//   }
//
//    void upload(String bucketName, File file) throws IOException {
//        try (final InputStream stream = new FileInputStream(file)) {
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentLength(file.length());
//            s3Client.putObject(
//                    new PutObjectRequest(bucketName, file.getName(), stream, metadata)
//            );
//        }
//    }
//
//    @Override
//    public Optional<InputStreamResource> download(String key) {
//        return s3Client.doesObjectExist(bucketName, key)
//                ? Optional.of(new InputStreamResource(s3Client.getObject(bucketName, key).getObjectContent()))
//                : Optional.empty();
//    }
//
//    @Override
//    public void deleteAll(String... keys) {
//        s3Client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
//    }
}

