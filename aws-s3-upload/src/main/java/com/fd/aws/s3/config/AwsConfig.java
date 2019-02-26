package com.fd.aws.s3.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fdanismaz
 * date: 12/18/18 10:13 PM
 */
@Configuration
public class AwsConfig {

    @Value("${aws.profile}")
    private String awsProfile;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonS3 awsS3Client() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(this.awsRegion)
                .withCredentials(new ProfileCredentialsProvider(this.awsProfile))
                .build();
        return s3Client;
    }

}
