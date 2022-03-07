package com.example.project1apitwo.controllers;

import com.example.project1apitwo.dto.MailDTO;
import com.example.project1apitwo.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @Slf4j
@RequestMapping("emails")
public class EmailApiController {
    private final EmailService emailService;

    /**
     * @param emailService Autowired EmailService
     */
    @Autowired
    public EmailApiController(EmailService emailService){
        this.emailService = emailService;
    }

    /**
     * @param list List of string containing information to be added to the email
     * @return Returns nothing accepted status code
     */
    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody List<String> list){
        log.info("Email getting ready to be sent");

        emailService.sendEmail(new MailDTO("emailapi84@gmail.com", list.get(0), "", "", "Employee Reimbursement", "Your Reimbursement for the amount $" +
                list.get(1) + " has been " + list.get(2) + ".", ""));

        return ResponseEntity.accepted().build();
    }
}
