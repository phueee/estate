package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    UserDao dao;
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String showLoginGet(Model model){
        model.addAttribute("title","login");
        return "login";
    }
    @PostMapping("/login")
    public String showLoginPost(@RequestParam String email,String password, Model model,HttpSession session){
        User user = userService.login(email,password);

        if (user == null) {
            model.addAttribute("error1","error");
            return "login";
        }
        else {
            session.setAttribute("userEmail", user.getUserEmail());

            return "index";
        }
    }



}
