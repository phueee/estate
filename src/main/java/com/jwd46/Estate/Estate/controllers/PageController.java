package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {
@Autowired
     HomeDao homeDao;
    @GetMapping("/")
    public String viewPage(Model model) {
        
        model.addAttribute("title", "Home");

        return "index";
    }
    @GetMapping("view")
    public String viewAbout(Model model) {
        List<Home> homes=homeDao.findAll();
        model.addAttribute("homes", homes);
        model.addAttribute("title", "About");
        return "view";
    }
    @GetMapping("appartment")
    public String viewAppartment(Model model) {
        List<Home> homes=homeDao.findAllByProperty("Appartment");
        model.addAttribute("homes", homes);
        model.addAttribute("title", "Appartment");
        return "property/appartment.html";
    }
    @GetMapping("villa")
    public String viewVilla(Model model){
        List<Home> homes=homeDao.findAllByProperty("Villa");
        model.addAttribute("homes", homes);
        model.addAttribute("title","Villa");
        return "property/villa.html";
    }
    @GetMapping("home")
    public String viewHome(Model model){
        List<Home> homes=homeDao.findAllByProperty("House");
        model.addAttribute("homes", homes);
        model.addAttribute("title","Home");
        return "property/home.html";
    }

    @GetMapping("office")
    public String viewOfficd(Model model){
        List<Home> homes=homeDao.findAllByProperty("Office");
        model.addAttribute("homes", homes);
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
