package com.example.project1apione;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Project1ApiOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project1ApiOneApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(){
        return args -> {

        };
    }
}
