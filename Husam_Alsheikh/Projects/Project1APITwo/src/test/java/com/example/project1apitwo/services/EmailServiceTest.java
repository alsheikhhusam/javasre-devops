package com.example.project1apitwo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest(classes = {EmailService.class, JavaMailSender.class})
public class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Autowired
    JavaMailSender javaMailSender;
}
