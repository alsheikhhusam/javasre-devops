package com.example.project1apitwo.controllers;

import com.example.project1apitwo.dto.MailDTO;
import com.example.project1apitwo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("emails")
public class EmailApiController {
    private final EmailService emailService;

    @Autowired
    public EmailApiController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody List<String> list){
        emailService.sendEmail(new MailDTO("emailapi84@gmail.com", list.get(0), "", "", "Employee Reimbursement", "Your Reimbursement for the amount $" +
                list.get(1) + " has been " + list.get(2) + ".", ""));

        return ResponseEntity.accepted().build();
    }
}
