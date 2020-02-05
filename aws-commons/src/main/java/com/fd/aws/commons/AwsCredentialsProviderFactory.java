package com.fd.aws.commons;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

/**
 * @author fdanismaz
 * @since 2/5/20
 */
public class AwsCredentialsProviderFactory {

    private AWSCredentialsProvider provider;

    public AwsCredentialsProviderFactory() {
        this.provider = DefaultAWSCredentialsProviderChain.getInstance();
    }

    public AWSCredentialsProvider getDefaultCredentialsProvider() {
        return this.provider;
    }

}
