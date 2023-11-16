package com.jwd46.Estate.Estate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {
    @GetMapping("/signup")
    public String showSign(Model model){
        model.addAttribute("tile","signup");
        return "signup";
    }
}
