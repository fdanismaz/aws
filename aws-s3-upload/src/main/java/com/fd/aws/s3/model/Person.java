package com.fd.aws.s3.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author fdanismaz
 * date: 12/18/18 11:54 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;
    private String name;
    private String surname;

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public static Person fromJson(String text) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(text, Person.class);
    }

    public static Person fromJson(InputStream stream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stream, Person.class);
    }
}
