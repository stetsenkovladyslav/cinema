package com.example.root.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@Import(LocalstackClientConfig.class)
public class AwsFileServiceImpl implements AwsFileService {

    private final AmazonS3 s3Client;
    private final String bucketName;

    @Autowired
    public AwsFileServiceImpl(AmazonS3 s3Client,
                              @Value("${aws.s3.bucket:bucket}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;

    }


    @Override
    public void upload(String key, InputStream inputStream) throws IOException {
        var metadata = new ObjectMetadata();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, byteArrayInputStream, metadata);
        s3Client.putObject(putObjectRequest);
    }


        @Override
    public Optional<InputStreamResource> download(String key) {
        return s3Client.doesObjectExist(bucketName, key)
                ? Optional.of(new InputStreamResource(s3Client.getObject(bucketName, key).getObjectContent()))
                : Optional.empty();
    }

    @Override
    public void deleteAll(String... keys) {
        s3Client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
    }

}

