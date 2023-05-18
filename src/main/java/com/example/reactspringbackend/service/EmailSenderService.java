package com.example.reactspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {

    @Value("07shakilhasan@gmail.com")
    private String senderEmailAddress;


    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String receiverEmailAddress,
                          String body,
                          String subject) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmailAddress);
        message.setTo(receiverEmailAddress);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
