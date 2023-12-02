package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.AdminDao;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.Service.HomeService;
import com.jwd46.Estate.Estate.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class AdminController {


    @Autowired
    AdminDao dao;
    @Autowired
    HomeDao homeDao;
    @Autowired
    HomeService homeService;
    @GetMapping("/adminlogin")
    public String viewadminlogin(Model model) {
        model.addAttribute("title", "Adminlogin");
        return "adminlogin";
    }


    @PostMapping("/adminlogin")
    public String showSignPost(Model model, @RequestParam String email, String password) {
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);
        if (admin.getEmail().equals("Phyoke Kya Ml@gmail.com") && admin.getPassword().equals("123123")) {
            return "adminview";
        } else {
            return "adminlogin";
        }

    }

    @GetMapping("/adminCreate")
    public String createHome (Model model){
        model.addAttribute("title", "adminCreate");
        return "adminCreate";
    }

    @RequestMapping("adminView")
    public String adminView (Model model){
        model.addAttribute("title", "adminView");
        return "adminView";
    }
    @GetMapping("/homes")
    public String viewHomes (Model model){
        model.addAttribute("title", "homes");
        return "homes";
    }
    @PostMapping("/homes")
    public String viewHomes(Model model, @RequestParam MultipartFile file, String homeNo, String bedRoom, String bathRoom, String area, String location, String price, String property, String service, String photo){
        homeService.saveHomeToDB(file,homeNo,bedRoom,bathRoom,area,location,price,property,service,photo);
        return "redirect:/homes";
    }
    @RequestMapping("/user")
    public String viewUsers (Model model){
        model.addAttribute("title", "users");
        return "user";
    }



}
