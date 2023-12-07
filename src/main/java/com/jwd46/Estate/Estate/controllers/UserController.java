package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserDao dao;


    @GetMapping("/signup")
    public String showSignGet(){
        return "signup";
    }

    @PostMapping("/signup")
    public String showSignPost(Model model, @RequestParam String name, String email, String phone, String NRC, String password, HttpServletResponse response,HttpSession session) {
        User user = new User();
        user.setUserName(name);
        user.setUserEmail(email);
        user.setUserPhone(phone);
        user.setUserNrc(NRC);
        user.setUserPassword(password);
        dao.save(user);
        if (user.getUserName().equals("") || user.getUserEmail().equals("") || user.getUserPhone().equals("") || user.getUserNrc().equals("") || user.getUserPassword().equals("")) {
            model.addAttribute("error", "Please fill required informations!");
            return "signup";
        } else {
            session.setAttribute("userEmail", user.getUserEmail());
            Cookie ck=new Cookie("email",email);
            ck.setMaxAge(60*60*24);
            response.addCookie(ck);
            return "index";
        }
    }

    @GetMapping("/user")
    public String User(Model model){
        List<User> users=dao.findAll();
        model.addAttribute("users",users);
        return "user";
    }

    @PostMapping("/user")
    public String User(Model model, @RequestParam String userName){
        User user=new User();
        user.setUserName(userName);
        dao.save(user);
        return "redirect:/user";

    }

    //@PostMapping("/delete")
    //public String delete(Model model, @RequestParam String userId){
      //  User user=new User();
        //dao.delete(user);
        //return "redirect:/user";



//
//    @DeleteMapping("/delete/{userId}")
//    public String delete(@PathVariable int userId){
//        userService.deleteUser(userId);
//        return "user";
//     }


    @GetMapping("/delete/user/{userId}")
    public String deleteUser(@PathVariable("userId") int userId){
        dao.deleteById(userId);
        return "redirect:/user";

    }



    }


