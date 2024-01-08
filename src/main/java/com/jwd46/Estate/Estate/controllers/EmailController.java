package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.EmailService;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.PaymentDao;
import com.jwd46.Estate.Estate.daos.RPaymentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.Payment;
import com.jwd46.Estate.Estate.models.RPayment;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    HomeDao homeDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    RPaymentDao rPaymentDao;


    @GetMapping("/email")
    public  String homePage(){
        return "email";
    }



    @PostMapping("/send/email/admin")
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



    @PostMapping("/send/email/user")
    public String sendEmailForAdmin(@RequestParam("toEmail")String toEmail,
                                    @RequestParam("subject")String subject,
                                    @RequestParam("body")String body,
                                    @RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes,HttpSession session){

        try{
            emailService.sendEmailWithAttachment(toEmail, subject, body, file);
            redirectAttributes.addFlashAttribute("success",true);
            int id=(int) session.getAttribute("homeIDSession");
            Home home=homeDao.getHomeById(id);
            home.setStatus(0);

            int id1=(int) session.getAttribute("RPaymentSession2");
            User user=userDao.findByUserId(id1);

            String price=(String) session.getAttribute("RPaymentSession3");

            LocalDateTime end_date=(LocalDateTime) session.getAttribute("RPaymentSession4");
            RPayment rPayment=new RPayment();
            rPayment.setUser(user);
            rPayment.setHome(home);
            rPayment.setPrice(price);
            rPayment.setStartDate(LocalDateTime.now());
            rPayment.setEndDate(end_date);
//            rPaymentDao.save(rPayment);
            saveRPayment(rPayment);

            int homeId=(int) session.getAttribute("homeID");
            Home home1=homeDao.getHomeById(homeId);
            home1.setStatus(0);
            int userId=(int) session.getAttribute("userID");
            User user2=userDao.findByUserId(userId);
            Payment payment=new Payment();
            payment.setUser(user2);
            payment.setHome(home1);
            payment.setDateTime(LocalDateTime.now());
//            paymentDao.save(payment);
            savePayment(payment);
            return "redirect:/index";
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error",true);
            return "redirect:/index";
        }
    }
    @Transactional
    public void saveRPayment(RPayment rPayment) {
        rPaymentDao.save(rPayment);
    }

    @Transactional
    public void savePayment(Payment payment) {
        paymentDao.save(payment);
    }


}