package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuyController {
    @Autowired
    UserService userService;



//    @GetMapping("/rent")
//    public String viewRent(Model model){
//        model.addAttribute("title","Rentview");
//        return "/service/rent";
//    }


    @GetMapping("/rent")
    public String rentLogin(Model model){
        model.addAttribute("title","Rent");
        return "rentlogin";
    }

//    @PostMapping("/rent")
//    public String rentDetail(@RequestParam String homeNo, String bedroom,String bathroom,String homeArea,String homeLocation,String homePrice,String startDate,String endDate, Model model, HttpSession session){
////        User user = userService.login(email,password);
////
////        if (user == null) {
////            model.addAttribute("error","error");
////            return "rentlogin";
////        }
////        else {
//
////            session.setAttribute("userEmail", user.getUserEmail());
//          model.addAttribute("Home", new Home());
//          return "Rpayment ";
//
//    }

//    @PostMapping("/rent")
//    public String rentDetails(@RequestParam String email,String password, HttpServletRequest request){
//        User user=userService.login(email, password);
//
//
//    }

    @GetMapping("/buy")
    public String viewBuy(Model model, HttpSession session){
//        model.addAttribute("title","Buyview");
        User user= (User) session.getAttribute("userEmail");
        if(user != null){
            return "/service/buy";
        }else {
            return "login";
        }
    }

    @GetMapping("/buylogin")
    public String showBuy(Model model){
        model.addAttribute("Home",new Home());
        return "buylogin";
    }

    @PostMapping("/buylogin")
    public String showBuy(@RequestParam String email, String password, Model model, HttpServletRequest request){
        User user = userService.login(email,password);

        if (user == null) {
            model.addAttribute("error","error");
            return "login";
        }
        else {
            request.getSession().getAttribute("userEmail");
            //  session.setAttribute("userEmail", user.getUserEmail());
//       model.addAttribute("Home", new Home());
            return "buy";

        }
    }

    }

