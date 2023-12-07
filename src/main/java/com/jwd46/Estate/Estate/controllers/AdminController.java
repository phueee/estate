package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.AdminDao;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.Service.HomeService;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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



    @PostMapping("/adminCreate")
    public String viewHomes(@RequestParam MultipartFile file,String inputHomeNo,String inputNo_of_Bedroom,String inputNo_of_Bathroom,String inputArea,String inputLocation,String inputPrice,String inputProperty,String inputService,String photo,Model model){

//        homeService.saveHomeToDB(file,homeNo,bedroom,bathroom,area,location,price,property,service,photo);
        Home home=new Home();
        home.setHomeNo(inputHomeNo);
        home.setBedRoom(inputNo_of_Bedroom);
        home.setBathRoom(inputNo_of_Bathroom);
        home.setArea(inputArea);
        home.setLocation(inputLocation);
        home.setPrice(inputPrice);
        home.setProperty(inputProperty);
        home.setService(inputService);
        homeDao.save(home);
//        model.addAttribute("home",new Home());
        return "redirect:/homes";
    }


    @RequestMapping("/user")
    public String viewUsers (Model model){
        model.addAttribute("title", "users");
        return "user";
    }

    @RequestMapping("/adminEdit")
    public String homeEdit(Model model){
        model.addAttribute("title","adminEdit");
        return "adminEdit";
    }

}
