package com.fd.aws.commons;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

/**
 * @author fdanismaz
 * @since 2/5/20
 */
public class AwsSdkClientFactory {

    private final AwsCredentialsProviderFactory credentialProvider;

    public AwsSdkClientFactory(AwsCredentialsProviderFactory credentialProvider) {
        this.credentialProvider = credentialProvider;
    }

    public AmazonSQS getAwsSqsClient() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(5000);
        clientConfiguration.setSocketTimeout(25000);

        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(this.credentialProvider.getDefaultCredentialsProvider())
                .withClientConfiguration(clientConfiguration)
                .build();
        return sqsClient;
    }

    public AmazonSNS getAwsSnsClient() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();

        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(this.credentialProvider.getDefaultCredentialsProvider())
                .build();
        return snsClient;
    }
}
