package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.UserDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    UserDao dao;

    @GetMapping("/login")
    public String showLoginGet(Model model){
        model.addAttribute("title","login");
        return "login";
    }
    @GetMapping("/adminlogin")
    public String viewadminlogin(Model model){
        model.addAttribute("title","Adminlogin");
        return "adminlogin.html";
    }




}
