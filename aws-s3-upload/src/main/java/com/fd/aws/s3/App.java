package com.fd.aws.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author fdanismaz
 * date: 12/18/18 9:17 PM
 */
@Slf4j
@ComponentScan("com.fd")
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
        log.info("Application started...");
    }
}
