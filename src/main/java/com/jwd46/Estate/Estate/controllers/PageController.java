package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {
@Autowired
private HomeDao homeDao;
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
        return "view.html";
    }

    @GetMapping("appartment")
    public String viewAppartment(Model model) {
        List<Home> homes=homeDao.findAllByProperty("");
        System.out.println("homes");
        model.addAttribute("homes", homes);
        model.addAttribute("title", "Appartment");
        return "view.html";
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
        List<Home> homes=homeDao.findAllByProperty("Home");
        model.addAttribute("homes", homes);
        model.addAttribute("title","Home");
        return "property/home.html";
    }

    @GetMapping("office")
    public String viewOffice(Model model){
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

    @GetMapping("Buy")
    public String viewBuy(Model model){
        List<Home> homes=homeDao.findByService("Sale");
        model.addAttribute("homes", homes);
        model.addAttribute("title","Buyview");
        return "service/Buy.html";
    }
    @GetMapping("Rent")
    public String viewRent(Model model){
        List<Home> homes=homeDao.findByService("Rent");
        model.addAttribute("homes", homes);
        model.addAttribute("title","Rentview");
        return "service/Rent.html";
    }

    @GetMapping("userdetail")
    public String viewUserdetail(Model model){
        model.addAttribute("title","userdetail");
        return "userdetail.html";
    }
}
