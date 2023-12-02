package com.jwd46.Estate.Estate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
    @GetMapping("payment")
    public String viewPayment(Model model){
        model.addAttribute("title","Payment");
                return "payment";
    }


    @GetMapping("Rpayment")
    public String viewRPayment(Model model){
        model.addAttribute("title","RPayment");
        return "Rpayment";
    }


    @GetMapping("/detail")
    public String viewDetail(Model model){
        model.addAttribute("title","Detail");
        return "details";
    }

    @GetMapping("/Rdetail")
    public String viewRDetail(Model model){
        model.addAttribute("title","RDetail");
        return "Rdetails";
    }


}
