package com.fd.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fd.aws.commons.AwsCredentialsProviderFactory;
import com.fd.aws.commons.AwsSdkClientFactory;

import java.io.IOException;

/**
 * @author fdanismaz
 * @since 2/4/20
 */
public class SnsTestTopicHandler implements RequestHandler<SNSEvent, Void> {

    @Override
    public Void handleRequest(SNSEvent input, Context context) {
        String message = input.getRecords().get(0).getSNS().getMessage();

        ObjectMapper mapper = new ObjectMapper();
        try {
            Person person = mapper.readValue(message, Person.class);
            String serializedPerson = mapper.writeValueAsString(person);

            AwsSdkClientFactory awsSdkClientFactory = new AwsSdkClientFactory(new AwsCredentialsProviderFactory());
            AmazonSQS sqsClient = awsSdkClientFactory.getAwsSqsClient();
            String queueUrl = "https://sqs.us-east-1.amazonaws.com/902703094694/FurkanTest";
            SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, serializedPerson);
            sqsClient.sendMessage(sendMessageRequest);

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }
}
