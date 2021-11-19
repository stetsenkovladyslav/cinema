package com.example.admin.configuration;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("dev")
@Configuration
@AllArgsConstructor
public class LocalstackClientConfig {

    @Bean
    public AmazonS3 amazonS3( @Value("${aws.s3-bucket}") String bucketName,
                              @Value("${aws.localstack-url}") String localhost) {
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