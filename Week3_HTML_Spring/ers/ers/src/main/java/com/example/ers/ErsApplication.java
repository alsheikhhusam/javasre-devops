package com.example.ers;

import com.example.ers.services.ReimbsUserService;
import com.example.ers.services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class ErsApplication {

    @Autowired
    ReimbsUserService service;

    @Autowired
    ReimbursementService rService;

    public static void main(String[] args) {
        SpringApplication.run(ErsApplication.class, args);
    }


}
