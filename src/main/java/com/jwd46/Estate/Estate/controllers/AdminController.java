package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.AdminDao;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.Service.HomeService;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    public String showSignPost(Model model, @RequestParam String email, String password, HttpSession session, HttpServletRequest request) {
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);
        if (admin.getEmail().equals("Phyoke Kya Ml@gmail.com") && admin.getPassword().equals("123123")) {
//            session.setAttribute("admin", admin);
            request.getSession().setAttribute("admin",admin);

            return "redirect:/homes";
        } else {
            return "adminlogin";
        }


    }

    @GetMapping("/adminCreate")
    public String createHome(Model model, HttpServletRequest request) {
        model.addAttribute("title", "adminCreate");
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        if (admin != null) {
            return "adminCreate";
        } else {
            return "redirect:/";
        }
//        return "adminCreate";
    }

    @RequestMapping("adminView")
    public String adminView(Model model, HttpServletRequest request) {
        model.addAttribute("title", "adminView");
        Admin admin= (Admin) request.getSession().getAttribute("admin");

        if (admin != null) {
            return "adminView";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/homes")
    public String viewHomes(HttpServletRequest request,Model model) {
        List<Home> homes = homeDao.findAll();
        List<Home> listHome = new ArrayList<>();
        model.addAttribute("homes", listHome);
        for (Home home : homes){
            if(home.isActive()==true){
                listHome.add(home);
            }
        }
        return "homes";
//        Admin admin = (Admin) session.getAttribute("admin");
//        if (admin == null) {
//            return "redirect:/";
//        } else {
//
//            return "homes";
//        }
    }





   @PostMapping("/adminCreate")
   public String viewHomesDetail(@RequestParam MultipartFile file, String homeNo, String bedRoom, String bathRoom, String area,String location,String price,String property,String service,String photo) {
       homeService.saveHomeToDB(file,homeNo,bedRoom,bathRoom,area,location,price,property,service,photo);
       return "redirect:/homes";
   }
//

//    @RequestMapping("/user")
//    public String viewUsers(Model model, HttpSession session) {
//        List<User> users = userDao.findAll();
//        model.addAttribute("users", users);
//        Admin admin = (Admin) session.getAttribute("admin");
//        if (admin != null) {
//            return "user";
//        } else {
//            return "redirect:/";
//        }
//    }
//
//    @PostMapping("/user")
//    public String User(Model model, @RequestParam String userName, HttpSession session) {
//        User user = new User();
//        user.setUserName(userName);
//        userDao.save(user);
//        Admin admin = (Admin) session.getAttribute("admin");
//        if (admin != null) {
//            return "redirect:/user";
//        } else {
//            return "redirect:/";
//        }
//    }



    @GetMapping("/adminEmail")
    public  String sendEmail(){
        return "adminEmail";
    }
}
