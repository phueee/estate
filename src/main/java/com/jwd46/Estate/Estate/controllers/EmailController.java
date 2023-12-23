package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.EmailService;
import com.jwd46.Estate.Estate.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Email email) {
        emailService.sendEmail(email.getTo(), email.getSubject(), email.getBody());
        return "Email sent successfully";
    }
}
