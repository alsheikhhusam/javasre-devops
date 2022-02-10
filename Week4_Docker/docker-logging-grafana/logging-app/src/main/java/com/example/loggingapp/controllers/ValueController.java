package com.example.loggingapp.controllers;

import com.example.loggingapp.dto.ValueResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/values")
public class ValueController {
    List<ValueResponse> valuesResponses;

    Logger logger = LoggerFactory.getLogger(ValueController.class);

    @PostConstruct
    public void init() {
//        MDC.put("initializing", "Value Controller -- value responses");
        logger.info("Initializing value responses");
        valuesResponses = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            String key = UUID.randomUUID().toString();
            int value = new Random().ints(3, 10000, 1000000).findAny().getAsInt();
            valuesResponses.add(new ValueResponse(key, value));
        }
        logger.info("Value response initialized");
//        MDC.clear();
    }

    @GetMapping
    public ResponseEntity<List<ValueResponse>> getValues(HttpServletRequest request) {
            logger.info("Processing request for all values");
        return ResponseEntity.ok(valuesResponses);
    }

    @GetMapping("{key}")
    public ResponseEntity<ValueResponse> getValueMapping(@PathVariable String key) {
        ValueResponse response = valuesResponses.stream()
                .filter(r -> r.getKey().equals(key))
                .findFirst().orElse(null);

        if(response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }


}
