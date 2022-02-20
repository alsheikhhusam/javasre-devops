package com.example.kubernetesdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KubernetesDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KubernetesDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(){
        return args -> {
            System.out.println("I am not very creative. And that's OK");
        };
    }
}
