package com.fd.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fd.aws.lambda.model.Person;

import java.io.IOException;

/**
 * @author fdanismaz
 * @since 2/4/20
 */
public class SnsTestTopicHandler implements RequestHandler<SNSEvent, Person> {

    @Override
    public Person handleRequest(SNSEvent input, Context context) {
        String message = input.getRecords().get(0).getSNS().getMessage();

        ObjectMapper mapper = new ObjectMapper();
        try {
            Person person = mapper.readValue(message, Person.class);
            return person;
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
