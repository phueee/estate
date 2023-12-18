package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BuyController {
    @Autowired
    UserService userService;
    @Autowired
    HomeDao homeDao;


//    @GetMapping("/rent")
//    public String viewRent(Model model){
//        model.addAttribute("title","Rentview");
//        return "/service/rent";
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



    @GetMapping("/rent")
    public String viewRent(Model model, HttpSession session){
        User user= (User) session.getAttribute("userEmail");
        if(user != null){
            return "/service/rent";
        }else {
            return "login";
        }
    }

    @GetMapping("/rentlogin/{homeId}")
    public String showRent(Model model,@PathVariable("homeId") int homeId,HttpSession session){
        Home home=homeDao.findByHomeId(homeId);
        session.setAttribute("home45",home);
        return "rentlogin";
    }



    @PostMapping("/rentlogin/user")
    public String showRent(@RequestParam String email, String password, Model model, HttpSession session){
        User user = userService.login(email,password);

        if (user == null) {
            model.addAttribute("error","error");
            return "rentlogin";
        }
        else {
            session.setAttribute("userEmail", user.getUserEmail());
            Home home=(Home) session.getAttribute("home45");
            model.addAttribute("homeNo",home.getHomeNo());
            model.addAttribute("bedRoom",home.getBedRoom());
            model.addAttribute("bathRoom",home.getBathRoom());
            model.addAttribute("area",home.getArea());
            model.addAttribute("location",home.getLocation());
            model.addAttribute("price",home.getPrice());
            model.addAttribute("currentDate", LocalDate.now());
            LocalDate currentDate = LocalDate.now();
            LocalDate dateThreeMonthsLater = currentDate.plusMonths(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = dateThreeMonthsLater.format(formatter);

            model.addAttribute("dateThreeMonthsLater", formattedDate);

            return "rent";
        }


    }



    @GetMapping("/buylogin/{homeId}")
    public String showBuy(Model model,@PathVariable("homeId") int homeId,HttpSession session){
//        System.out.println(homeId);
        Home home=homeDao.findByHomeId(homeId);
//        System.out.println(home.getHomeId());
        session.setAttribute("home45",home);
        return "buylogin";
    }

    @PostMapping("/buylogin/user")
    public String showBuy(@RequestParam String email, String password, Model model, HttpSession session){
        User user = userService.login(email,password);

        if (user == null) {
            model.addAttribute("error","error");
            return "buylogin";
        }
        else {
            session.setAttribute("userEmail", user.getUserEmail());
            Home home=(Home) session.getAttribute("home45");
            model.addAttribute("homeNo",home.getHomeNo());
            model.addAttribute("bedRoom",home.getBedRoom());
            model.addAttribute("bathRoom",home.getBathRoom());
            model.addAttribute("area",home.getArea());
            model.addAttribute("location",home.getLocation());
            model.addAttribute("price",home.getPrice());
            model.addAttribute("currentDate", LocalDate.now());
            return "buy";
        }


    }

//    @GetMapping("/login/signup/{homeId}")
//    public String buyPage(@PathVariable("homeId") int homeId,HttpSession session){
//        session.setAttribute("home456",homeId);
//        return "buy";
//    }

}
