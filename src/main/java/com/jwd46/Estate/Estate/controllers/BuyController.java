package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuyController {
    @Autowired
    UserService userService;
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


        @GetMapping("/rentlogin")
    public String login(Model model){
            model.addAttribute("title", "Rent");
            return "rentlogin";
        }


    @PostMapping("/rentlogin")
    public String showLoginPost(@RequestParam String email, String password, Model model, HttpSession session){
        User user = userService.login(email,password);

        if (user == null) {
            model.addAttribute("error","error");
            return "rentlogin";
        }
        else {
            session.setAttribute("userEmail", user.getUserEmail());
            model.addAttribute("Home", new Home());
            return "rent";
        }
    }


    @GetMapping("/buylogin")
    public String showBuy(Model model){
        model.addAttribute("title", "Buy");
        return "buylogin";
    }

    @PostMapping("/buylogin")
    public String showBuy(@RequestParam String email, String password, Model model, HttpSession session){
        User user = userService.login(email,password);

        if (user == null) {
            model.addAttribute("error","error");
            return "login";
        }
        else {
            session.setAttribute("userEmail", user.getUserEmail());
            model.addAttribute("Home", new Home());
            return "buy";
        }
    }

    }

