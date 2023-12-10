package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
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
    @GetMapping("villa")
    public String viewVilla(Model model){
        model.addAttribute("title","Villa");
        return "property/villa.html";
    }
    @GetMapping("home")
    public String viewHome(Model model){
        model.addAttribute("title","Home");
        return "property/home.html";
    }

    @GetMapping("office")
    public String viewOfficd(Model model){
        model.addAttribute("title","Office");
        return "property/office.html";
    }

    @GetMapping("contact")
    public  String viewContact(Model model){
        model.addAttribute("title","Contact");
        return "contact.html";
    }

    @GetMapping("villadetail")
    public String viewVilladetail(Model model){
        return "property/villadetail";
    }


    @GetMapping("userdetail")
    public String viewUserdetail(Model model){
        model.addAttribute("title","userdetail");
        return "userdetail.html";
    }
}
