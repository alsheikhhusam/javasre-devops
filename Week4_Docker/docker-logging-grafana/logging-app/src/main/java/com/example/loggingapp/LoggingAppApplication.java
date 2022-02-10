package com.example.loggingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoggingAppApplication {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    Logger logger = LoggerFactory.getLogger(LoggingAppApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LoggingAppApplication.class, args);
    }

}
