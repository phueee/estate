package com.jwd46.Estate.Estate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String viewPage(Model model) {
        model.addAttribute("title", "Home");
        return "index";
    }

    @GetMapping("view")
    public String viewAbout(Model model) {
        model.addAttribute("title", "About");
        return "view.html";
    }

    @GetMapping("appartment")
    public String viewAppartment(Model model) {
        model.addAttribute("title", "Appartment");
        return "property/appartment.html";
    }
}