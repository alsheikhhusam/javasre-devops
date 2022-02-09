package com.example.values_api_fixed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ValuesApiFixedApplication {

    private static final Logger logger = LoggerFactory.getLogger(ValuesApiFixedApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ValuesApiFixedApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            MDC.put("events", "Application Startup");
            logger.info("Starting");
            logger.info("Complete");
            MDC.clear();
        };
    }

}
