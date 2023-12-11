package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.PaymentDao;
import com.jwd46.Estate.Estate.daos.RPaymentDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.Payment;
import com.jwd46.Estate.Estate.models.RPayment;
import com.jwd46.Estate.Estate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    RPaymentDao rPaymentDao;

    @GetMapping("payment")
    public String viewPayment(Model model){
        model.addAttribute("title","Payment");
        model.addAttribute("User", new User());
                return "payment";
    }


    @GetMapping("Rpayment")
    public String viewRPayment(Model model){
        model.addAttribute("title","RPayment");
        model.addAttribute("User", new User());
        return "Rpayment";
    }

    @GetMapping("/detail")
    public String viewDetail(Model model){
        List<Payment> payments=paymentDao.findAll();
        model.addAttribute("payments",payments);
        return "details";
    }

    @PostMapping("/detail")
    public String viewDetail(Model model,@RequestParam String userName){
        Payment payment=new Payment();
        payment.setUserName(userName);
        paymentDao.save(payment);
        return "redirect:/details";

    }

    @GetMapping("/Rdetail")
    public String viewRDetail(Model model){
        List<RPayment> rPayments=rPaymentDao.findAll();
        model.addAttribute("rPayments",rPayments);
        return "Rdetails";
    }


    @PostMapping("/Rdetail")
    public String viewRdetail(Model model,@RequestParam String userName){
        RPayment rpayment=new RPayment();
        rpayment.setUserName(userName);
        rPaymentDao.save(rpayment);
        return "redirect:/Rdetails";

    }



}
