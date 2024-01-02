package com.jwd46.Estate.Estate.Service;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;



@Service
public class EmailService {

    @Autowired
   private JavaMailSender mailSender;

    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject,
                                        MultipartFile attachment) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("zinnia959704@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);

        mimeMessageHelper.setSubject(subject);

        File fileConvert=convertMultipartToFile(attachment);

        FileSystemResource fileSystemResource = new FileSystemResource(fileConvert);

        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        mailSender.send(mimeMessage);
        System.out.println("Mail send");
    }

    public File convertMultipartToFile(MultipartFile file) throws Exception{
    File convertFile=new File(file.getOriginalFilename());
    try(OutputStream os=new FileOutputStream(convertFile)) {
        os.write(file.getBytes());
    }
    return convertFile;
    }

}

