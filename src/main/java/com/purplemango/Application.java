package com.purplemango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication(scanBasePackages = "com.purplemango")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
