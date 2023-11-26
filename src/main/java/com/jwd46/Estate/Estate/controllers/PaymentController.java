package com.jwd46.Estate.Estate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
    @GetMapping("/payment")
    public String viewPayment(Model model){
        model.addAttribute("title","Payment");
                return "payment";
    }

}
