package com.example.api2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api2")
public class Api2Controller {
    @PostMapping
    public ResponseEntity acknowlegeReceipt(@RequestBody String message) {
        System.out.println("Received message " + message);

        // start a thread to send the message somewhere else

        // return accepted
        return ResponseEntity.accepted().build();
    }
}
