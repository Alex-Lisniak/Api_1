package com.firstapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FirstApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstApiApplication.class, args);
    }

}
