package com.example.root.aws;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("dev")
@Configuration
public class LocalstackClientConfig {


    private final String bucketName;

    public LocalstackClientConfig(@Value("${aws.s3-bucket}") String bucketName) {
        this.bucketName = bucketName;
    }

    @Bean
    public AwsFileService awsFileService(@Autowired AmazonS3 s3Client, @Value("${aws.s3-bucket}") String bucketName) {
        return new AwsFileServiceImpl(s3Client, bucketName);
    }

    @Bean
    public AmazonS3 s3Client(@Value("http://localhost:4566") String localhost) {
        AmazonS3 localstackClient = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localhost, "us-west-1"))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("accesskey", "secretkey")))
                .withPathStyleAccessEnabled(true)
                .build();
        if (!localstackClient.doesBucketExistV2(bucketName)) {
            localstackClient.createBucket(bucketName);
        }
        return localstackClient;
    }
}