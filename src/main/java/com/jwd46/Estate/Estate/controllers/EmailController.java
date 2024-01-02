package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;


    @GetMapping("/email")
    public  String homePage(){
        return "email";
    }

    @PostMapping("/send/email/user")
    public String sendEmailForAdmin(@RequestParam("toEmail")String toEmail,
                                    @RequestParam("subject")String subject,
                                    @RequestParam("body")String body,
                                    @RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){

        try{
           emailService.sendEmailWithAttachment(toEmail, subject, body, file);
           redirectAttributes.addFlashAttribute("success",true);
            return "redirect:/index";
        }catch (Exception e){
       e.printStackTrace();
            redirectAttributes.addFlashAttribute("error",true);
            return "redirect:/index";
        }
    }

}
