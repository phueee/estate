package com.jwd46.Estate.Estate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyController {
    @GetMapping("buy")
    public String viewBuy(Model model) {
        model.addAttribute("title", "Buy");
        return "buy.html";
    }

        @GetMapping("rent")
        public String viewRent(Model model) {
            model.addAttribute("title", "Rent");
            return "rent.html";
        }
    }

