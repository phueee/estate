package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.HomeService;
import com.jwd46.Estate.Estate.daos.AdminDao;
import com.jwd46.Estate.Estate.daos.AppoinmentDao;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Appoinment;
import com.jwd46.Estate.Estate.models.Home;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    @Autowired
    AdminDao adminDao;
    @Autowired
    AppoinmentDao dao1;

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
        if (admin.getEmail().equals("phyokekyaml@gmail.com") && admin.getPassword().equals("123123")) {
            request.getSession().setAttribute("admin", admin);
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
    }

    @RequestMapping("adminView")
    public String adminView(Model model, HttpServletRequest request) {
        model.addAttribute("title", "adminView");
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            return "adminView";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/homes")
    public String viewHomes(HttpServletRequest request, Model model) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            List<Home> homes = homeDao.findAll();
            List<Home> listHome = new ArrayList<>();
            model.addAttribute("homes", listHome);
            for (Home home : homes) {
                if (home.isActive() == true) {
                    listHome.add(home);
                }
            }
            return "homes";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/adminCreate")
    public String viewHomesDetail(@RequestParam MultipartFile file, String homeNo, String bedRoom, String bathRoom, String area, String location, String price, String property, String service, String photo) {
        homeService.saveHomeToDB(file, homeNo, bedRoom, bathRoom, area, location, price, property, service, photo);
        return "redirect:/homes";
    }


    @GetMapping("/adminEmail/{id}")
    public ModelAndView sendEmail(@PathVariable("id") int id){
        Appoinment appoinment=dao1.findById(id).orElseThrow();
        return new ModelAndView("adminEmail","appointmentBean",appoinment);
    }
}