package com.example.admin.configuration;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


@Profile("dev")
@Configuration
@AllArgsConstructor
public class LocalstackClientConfig {

    @Bean
    public S3Client awsS3Client(@NonNull @Value("${aws.s3-bucket}") String s3Bucket,
                                @NonNull @Value("${aws.localstack-url}") String localstackUrl) throws MalformedURLException, URISyntaxException {
        S3Configuration s3Configuration = S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build();
        S3Client ret = S3Client.builder()
                .serviceConfiguration(s3Configuration)
                .endpointOverride(new URL(localstackUrl).toURI())
                .region(Region.US_WEST_1)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("accesskey", "secretkey")))
                .build();
        try {
            ret.headBucket(builder -> builder.bucket(s3Bucket));
        } catch (NoSuchKeyException | NoSuchBucketException e) {
            ret.createBucket(builder -> builder.bucket(s3Bucket).build());
        }
        return ret;
    }
}