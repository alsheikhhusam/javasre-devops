package com.example.project1apitwo;

import com.example.project1apitwo.dto.MailDTO;
import com.example.project1apitwo.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailServiceTest {
    private JavaMailSender javaMailSender;
    private EmailService emailService;
    private MimeMessage mimeMessage;

    @BeforeEach
    public void initBeforeEach(){
        mimeMessage = new MimeMessage((Session) null);
        javaMailSender = mock(JavaMailSender.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        emailService = new EmailService(javaMailSender);
    }

    @Test
    public void sendMailTest() throws MessagingException {
        String mailTo = "to@gmail.com";
        MailDTO mail = new MailDTO("", mailTo, "", "", "", "", "");

        emailService.sendEmail(mail);
        assertEquals(mailTo, mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString());
    }
}
