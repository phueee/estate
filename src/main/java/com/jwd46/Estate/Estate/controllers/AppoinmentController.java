package com.jwd46.Estate.Estate.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class AppoinmentController {


    @GetMapping("/getAppoinment")
    public String showBuy(Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("userEmail") != null) {
            model.addAttribute("currentDate", LocalDate.now());
            return "appoinment";
        } else {
            return "login";
        }

    }
}