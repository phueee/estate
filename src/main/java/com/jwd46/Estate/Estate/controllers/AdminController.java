package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.AdminDao;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.Service.HomeService;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller

public class AdminController {


    @Autowired
    AdminDao dao;
    @Autowired
    HomeDao homeDao;
    @Autowired
    HomeService homeService;
    @Autowired
    UserDao userDao;

    @GetMapping("/adminlogin")
    public String viewadminlogin(Model model) {
        model.addAttribute("title", "AdminLogin");
        return "adminlogin";
    }

    @PostMapping("/adminlogin")
    public String showSignPost(Model model, @RequestParam String email, String password, HttpSession session) {
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);
        if (admin.getEmail().equals("Phyoke Kya Ml@gmail.com") && admin.getPassword().equals("123123")) {
            session.setAttribute("admin", admin);
            return "adminView";
        } else {
            return "adminlogin";
        }

    }

    @GetMapping("/adminCreate")
    public String createHome(Model model, HttpSession session) {
        model.addAttribute("title", "adminCreate");
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return "adminCreate";
        } else {
            return "redirect:/";
        }
//        return "adminCreate";
    }

    @RequestMapping("adminView")
    public String adminView(Model model, HttpSession session) {
        model.addAttribute("title", "adminView");
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return "adminView";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/homes")
    public String viewHomes(HttpSession session,Model model) {
        List<Home> homes = homeDao.findAll();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            model.addAttribute("homes", homes) ;
            return "homes";
        } else {
            return "redirect:/";
        }
    }


   @PostMapping("/adminCreate")
   public String viewHomesDetail(@RequestParam MultipartFile file, String homeNo, String bedRoom, String bathRoom, String area,String location,String price,String property,String service,String photo) {
       homeService.saveHomeToDB(file,homeNo,bedRoom,bathRoom,area,location,price,property,service,photo);
       return "redirect:/homes";
   }
//        Home home = new Home();
//        home.setHomeNo(inputHomeNo);
//        home.setBedRoom(inputNo_of_Bedroom);
//        home.setBathRoom(inputNo_of_Bathroom);
//        home.setArea(inputArea);
//        home.setLocation(inputLocation);
//        home.setPrice(inputPrice);
//        home.setProperty(inputProperty);
//        home.setService(inputService);
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if (fileName.contains("..")) {
//            System.out.println("not a valid file");
//        }
//        try {
//            home.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        homeDao.save(home);
//        model.addAttribute("home", new Home());
//      Admin admin= (Admin) session.getAttribute("admin");
//        if(admin!=null)   {
//
////        return "redirect:/homes";
////        }else {
////           return "redirect:/";
////        }
//    }


    @RequestMapping("/user")
    public String viewUsers(Model model, HttpSession session) {
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return "user";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/user")
    public String User(Model model, @RequestParam String userName, HttpSession session) {
        User user = new User();
        user.setUserName(userName);
        userDao.save(user);
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return "redirect:/user";
        } else {
            return "redirect:/";
        }
    }
}
