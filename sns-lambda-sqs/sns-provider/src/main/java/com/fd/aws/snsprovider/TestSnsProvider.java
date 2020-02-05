package com.fd.aws.snsprovider;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fd.aws.commons.AwsCredentialsProviderFactory;
import com.fd.aws.commons.AwsSdkClientFactory;

/**
 * @author fdanismaz
 * @since 2/5/20
 */
public class TestSnsProvider {

    public static void main(String[] args) {

        AwsSdkClientFactory awsSdkClientFactory = new AwsSdkClientFactory(new AwsCredentialsProviderFactory());
        AmazonSNS snsClient = awsSdkClientFactory.getAwsSnsClient();

        String snsTopicArn = "...";
        Person person = new Person();
        person.setName("Cansu");
        person.setSurname("Danismaz");

        ObjectMapper mapper = new ObjectMapper();
        try {
            String message = mapper.writeValueAsString(person);
            PublishRequest publishRequest = new PublishRequest(snsTopicArn, message);
            snsClient.publish(publishRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
