package com.example.project1apitwo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emails")
public class EmailApiController {
    @Value("8081")
    int port;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody String status){
        System.out.println("Received message: " + status);

        return ResponseEntity.accepted().build();
    }
}
