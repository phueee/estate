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
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("/getAppoinment")
    public String showSignGet(){
        return "appoinment";
    }

    @PostMapping("/getAppoinment")
    public String showSignPost(Model model, @RequestParam String name, String email, String phone , String reason, String comment,LocalDateTime dateTime,HttpServletRequest request) {

        if (name.equals("") || email.equals("") || phone.equals("") || reason.equals("") || comment.equals("") || dateTime.equals("")) {
            model.addAttribute("error", "Please fill required informations!");
            return "appoinment";
        } else {
            Appoinment appoinment = new Appoinment();
            appoinment.setName(name);
            appoinment.setEmail(email);
            appoinment.setPhone(phone);
            appoinment.setReason(reason);
            appoinment.setComment(comment);
            appoinment.setDateTime(dateTime);
            appoinmentDao.save(appoinment);
            return "index";
        }
    }


//    @PostMapping("/delete/appoinment")
//    public String deleteAppoinment(@RequestParam int id) {
//        Appoinment appoinment = appoinmentDao.findById(id).orElseThrow();
//        appoinment.setActive(false);
//        appoinmentDao.save(appoinment);
//        return "redirect:/request";
//    }


    @PostMapping("/delete/appoinment")
    public String deleteAppoinment(@RequestParam int id) {
        appoinmentDao.deleteById(id);
        return "redirect:/request";

    }

}