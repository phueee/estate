package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpServletRequest;
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


    @GetMapping("/rentlogin/{homeId}")
    public String showRent(Model model, @PathVariable("homeId") int homeId, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("userEmail") != null) {
            Home home = homeDao.findByHomeId(homeId);
            model.addAttribute("home45", home);
            model.addAttribute("currentDate", LocalDate.now());
            LocalDate currentDate = LocalDate.now();
            LocalDate dateThreeMonthsLater = currentDate.plusMonths(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = dateThreeMonthsLater.format(formatter);

            model.addAttribute("dateThreeMonthsLater", formattedDate);

            return "rent";
        } else {
            return "rentlogin";
        }
    }

    @PostMapping("/rentlogin/user")
    public String showRent(@RequestParam String email, String password, Model model, HttpSession session) {
        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error","error");
            return "rentlogin";
        }
        else {

            session.setAttribute("userEmail", user.getUserEmail());
            session.setAttribute("userId", user.getUserId());
            return "redirect:/view";

        }
    }




    @GetMapping("/buylogin/{homeId}")
    public String showBuy(Model model, @PathVariable("homeId") int homeId, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("userEmail") != null) {
            Home home = homeDao.findByHomeId(homeId);
            model.addAttribute("home45", home);
            model.addAttribute("currentDate", LocalDate.now());

            return "buy";
        } else {
            return "buylogin";
        }
    }



    @PostMapping("/buylogin/user")
    public String showBuy(@RequestParam String email, String password, Model model, HttpSession session) {
        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error","error");
            return "buylogin";
        }
        else {

            session.setAttribute("userEmail", user.getUserEmail());
            session.setAttribute("userId", user.getUserId());
            return "redirect:/view";

        }
    }

}
