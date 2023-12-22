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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class BuyController {
    @Autowired
    UserService userService;
    @Autowired
    HomeDao homeDao;





    @GetMapping("/rentlogin/{homeId}")
    public String showRent(Model model, @PathVariable("homeId") int homeId, HttpSession session, HttpServletRequest request) {
        Home home = homeDao.findByHomeId(homeId);
//        session.setAttribute("home45", home);
        request.getSession().setAttribute("home45", home);
        return "rentlogin";
    }


    @PostMapping("/rentlogin/user")
    public String showRent(@RequestParam String email, String password, Model model, HttpSession session, HttpServletRequest request) {
        User user = userService.login(email, password);

        if (user == null) {
            model.addAttribute("error", "error");
            return "rentlogin";
        } else {
//            session.setAttribute("userEmail", user.getUserEmail());
//            Home home = (Home) session.getAttribute("home45");
            Home home = (Home) request.getSession().getAttribute("home45");
            model.addAttribute("homeNo", home.getHomeNo());
            model.addAttribute("bedRoom", home.getBedRoom());
            model.addAttribute("bathRoom", home.getBathRoom());
            model.addAttribute("area", home.getArea());
            model.addAttribute("location", home.getLocation());
            model.addAttribute("price", home.getPrice());
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
    public String showBuy(Model model, @PathVariable("homeId") int homeId, HttpSession session, HttpServletRequest request) {
        Home home = homeDao.findByHomeId(homeId);
        if (session.getAttribute("userEmail") != null) {
            model.addAttribute("home45", home);
            model.addAttribute("homeId", home.getHomeId());
            model.addAttribute("currentDate",LocalDate.now());
            return "buy";
        } else {
            session.setAttribute("homeID23",home);
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
      Home home=(Home)session.getAttribute("homeID23");
//      Integer home4=Integer.valueOf(home.getHomeId());
//      Home home1=homeDao.findByHomeId(home.getHomeId());
        session.setAttribute("userEmail", user.getUserEmail());
        session.setAttribute("userId", user.getUserId());
        return "redirect:/buylogin/" + home.getHomeId();


    }
}

    }



