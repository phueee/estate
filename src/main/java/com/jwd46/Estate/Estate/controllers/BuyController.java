package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.daos.UserHomeDao;
import com.jwd46.Estate.Estate.models.Admin;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import com.jwd46.Estate.Estate.models.UserHome;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BuyController {
    @Autowired
    UserService userService;
    @Autowired
    HomeDao homeDao;

    @Autowired
    UserHomeDao userHomeDao;
    @Autowired
    UserDao userDao;


    @GetMapping("/rentlogin/{homeId}")
    public String showRent(@PathVariable("homeId") int homeId, HttpSession session, Model model) {
        Home home = homeDao.findByHomeId(homeId);

        if (session.getAttribute("userEmail") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("home", home);
            model.addAttribute("user", user);
            model.addAttribute("currentDate", LocalDate.now());
            LocalDate currentDate = LocalDate.now();
            LocalDate dateThreeMonthsLater = currentDate.plusMonths(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = dateThreeMonthsLater.format(formatter);

            model.addAttribute("dateThreeMonthsLater", formattedDate);
            return "rent";
        } else {
            session.setAttribute("home", home);
            return "rentlogin";
        }
    }

    @PostMapping("/rentlogin/user")
    public String showRent(@RequestParam String email, String password, Model model, HttpSession session) {
        User user = userService.login(email, password);

        if (user == null) {
            return "rentlogin";
        } else {
            Home home=(Home)session.getAttribute("home");
            session.setAttribute("userEmail", user.getUserEmail());
            session.setAttribute("userName", user.getUserName());
            return "redirect:/rentlogin/" + home.getHomeId();
        }
    }



//
//@GetMapping("/buylogin/{homeId}")
//public String showBuy(@PathVariable("homeId") int homeId, HttpSession session, Model model) {
//    Home home = homeDao.findByHomeId(homeId);
//
//    if (session.getAttribute("userEmail") != null) {
//        User user = (User) session.getAttribute("user");
//        model.addAttribute("home", home);
//        model.addAttribute("user", user);
//        model.addAttribute("currentDate", LocalDate.now());
//        return "buy";
//    } else {
//        session.setAttribute("home", home);
//        return "buylogin";
//    }
//}
//
//    @PostMapping("/buylogin/user")
//    public String showBuy(@RequestParam String email, String password, Model model, HttpSession session) {
//        User user = userService.login(email, password);
//
//        if (user == null) {
//            return "buylogin";
//        } else {
//            Home home=(Home)session.getAttribute("home");
//            session.setAttribute("userEmail", user.getUserEmail());
//            session.setAttribute("userName", user.getUserName());
//            return "redirect:/buylogin/" + home.getHomeId();
//        }
//    }




    @GetMapping("/buyDetail/{homeId}")
    public String showBuy(@PathVariable("homeId") int homeId, HttpSession session, Model model) {

        if (session.getAttribute("userEmail") != null) {
            Home home = homeDao.findByHomeId(homeId);
            int userId= (Integer) session.getAttribute("userId");
            User user=userDao.findByUserId(userId);
            System.out.println(user.getUserName());
            model.addAttribute("home", home);
            model.addAttribute("user", user);
            model.addAttribute("currentDate", LocalDate.now());
//        UserHome userHome = new UserHome();
//        userHome.setHome(home);
//        userHome.setUser(user);
//        userHomeDao.save(userHome);
//        redirectAttributes.addAttribute("homeId", homeId);

            return "buy";
        } else {
//        session.setAttribute("home", home);
            return "redirect:/login";
        }
    }



    @PostMapping("/buylogin/user")
    public String showBuy(@RequestParam String email, String password, Model model, HttpSession session) {
        User user = userService.login(email, password);

        if (user == null) {
            return "buylogin";
        } else {
            Home home = (Home) session.getAttribute("home");

            if (home != null) {
                session.setAttribute("userEmail", user.getUserEmail());
                session.setAttribute("userName", user.getUserName());

                return "redirect:/buylogin/" + home.getHomeId();
            } else {
                // Handle the case where home is null
                return "buylogin";
            }
        }
    }



}