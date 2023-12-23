package com.jwd46.Estate.Estate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@gmail.com");
        message.setSubject("Meeting Invitation");
        message.setText("Hello");

        mailSender.send(message);
        System.out.println("mail send...");
    }
}
