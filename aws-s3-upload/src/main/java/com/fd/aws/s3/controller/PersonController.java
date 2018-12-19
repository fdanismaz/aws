package com.fd.aws.s3.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.fd.aws.s3.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
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
    private AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String awsS3Bucket;

    private static final String OBJECT_UPLOAD_FOLDER = "upload/object/";

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable int id) {
        try {
            S3Object object = this.s3client.getObject(new GetObjectRequest(this.awsS3Bucket, OBJECT_UPLOAD_FOLDER + id));
            Person p = Person.fromJson(object.getObjectContent());
            return new ResponseEntity<Person>(p, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() throws IOException {
        ObjectListing objectListing = this.s3client.listObjects(new ListObjectsRequest()
                        .withBucketName(this.awsS3Bucket)
                        .withPrefix(OBJECT_UPLOAD_FOLDER));
        List<Person> result = new ArrayList<Person>();
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String key = objectSummary.getKey();
            S3Object object = this.s3client.getObject(new GetObjectRequest(this.awsS3Bucket, key));
            Person p = Person.fromJson(object.getObjectContent());
            result.add(p);
        }
        return new ResponseEntity<List<Person>>(result, HttpStatus.OK);
    }

    @PostMapping
    public boolean save(@RequestBody Person person) {
        try {
            if (!this.s3client.doesBucketExistV2(this.awsS3Bucket)) {
                this.s3client.createBucket(new CreateBucketRequest(this.awsS3Bucket));
            }
            this.s3client.putObject(this.awsS3Bucket, OBJECT_UPLOAD_FOLDER + person.getId(), person.toJson());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        if (this.s3client.doesObjectExist(this.awsS3Bucket, OBJECT_UPLOAD_FOLDER + id)) {
            this.s3client.deleteObject(this.awsS3Bucket, OBJECT_UPLOAD_FOLDER + id);
            return true;
        }
        return false;
    }
}
