package com.fd.aws.s3.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.fd.aws.s3.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdanismaz
 * date: 12/18/18 11:54 PM
 */
@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    @Qualifier("awsS3PeopleClient")
    private AmazonS3 s3client;

    @Value("${aws.s3.bucket.test.people}")
    private String awsS3TestPeopleBucket;

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable int id) {
        Person p = Person.builder().name("furkan").surname("danismaz").build();
        return new ResponseEntity<Person>(p, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        Person p = Person.builder().name("furkan").surname("danismaz").build();
        List<Person> all = new ArrayList<Person>();
        all.add(p);
        return new ResponseEntity<List<Person>>(all, HttpStatus.OK);
    }

    @PostMapping
    public boolean save(@RequestBody Person person) {
        try {
            this.s3client.putObject(this.awsS3TestPeopleBucket, person.getName(), person.toString());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
