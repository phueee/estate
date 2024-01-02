package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.AppoinmentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Appoinment;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppoinmentController {

    @Autowired
    UserDao dao;

    @Autowired
    UserService userService;
    @Autowired
    AppoinmentDao appoinmentDao;
//
//    @GetMapping("/getAppoinment")
//    public String showBuy(Model model, HttpSession session, HttpServletRequest request) {
//        if (session.getAttribute("userEmail") != null) {
//            model.addAttribute("currentDate", LocalDate.now());
//            return "appoinment";
//        } else {
//            return "login";
//        }
//
//    }


    @GetMapping("/getAppoinment/{userId}")
    public String showAppoinment(@PathVariable("userId") int userId, HttpSession session, Model model, @RequestParam(required = false) String name, String email, String phone, String reason, String comment, LocalDateTime dateTime) {
        User user = dao.findByUserId(userId);
        Appoinment appoinment=new Appoinment();
        appoinment.setName(name);
        appoinment.setEmail(email);
        appoinment.setPhone(phone);
        appoinment.setReason(reason);
        appoinment.setComment(comment);
        appoinment.setDateTime(dateTime);
        if (session.getAttribute("userEmail") != null) {
            model.addAttribute("user", user);
            appoinmentDao.save(appoinment);
            return "appoinment";
        } else {
            session.setAttribute("user", user);
            return "login";
        }
    }

//    @PostMapping("/getAppoinment/{userId}")
//    public String showAppoinment(HttpSession session, Model model, @RequestParam(required = false) String name, String email,String password, String phone, String reason, String comment, LocalDateTime dateTime) {
//        User user = userService.login(email, password);
//        if (user == null) {
//            return "login";
//        } else {
//
//            session.setAttribute("userEmail",user.getUserEmail());
//            session.setAttribute("userName",user.getUserName());
//            Appoinment appoinment = new Appoinment();
//            appoinment.setName(name);
//            appoinment.setEmail(email);
//            appoinment.setPhone(phone);
//            appoinment.setReason(reason);
//            appoinment.setComment(comment);
//            appoinment.setDateTime(dateTime);
//            appoinmentDao.save(appoinment);
//            return "redirect:/appoinment";
//        }
//    }


    @GetMapping("/request")
    public String Appoinment (Model model){
        List<Appoinment> appoinments =appoinmentDao.findAll();
        model.addAttribute("appoinments",  appoinments);
        return "request";
    }

    @PostMapping("/request")
    public String Appoinment(Model model, @RequestParam(required = false) String name){
        Appoinment appoinment=new Appoinment();
        appoinment.setName(name);
        appoinmentDao.save(appoinment);
        return "redirect:/request";
    }

}