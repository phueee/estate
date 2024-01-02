package com.jwd46.Estate.Estate;

import com.jwd46.Estate.Estate.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class EstateApplication {
	@Autowired
	EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(EstateApplication.class, args);
	}

//  @EventListener(ApplicationReadyEvent.class)
//   public void triggerMail() throws MessagingException {
//
//	  emailService.sendEmailWithAttachment("phuesone05@gmail.com",
//			                               	"This is an appointment that we accepted",
//			                              	" you can see your appointment",
//			                               	"C:\\Users\\HP\\Pictures\\Screenshots\\Screenshot (63).png");
//
//   }


}
