package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;




@Controller
public class LoginController {

    @Autowired
    UserDao dao;
    @GetMapping("/login")
    public String showLoginGet(Model model){
        model.addAttribute("title","login");
        return "login";
    }


}
