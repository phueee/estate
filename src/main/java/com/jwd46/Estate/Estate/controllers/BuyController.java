package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.PaymentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.daos.UserHomeDao;
import com.jwd46.Estate.Estate.models.*;
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
    @Autowired
    PaymentDao paymentDao;

    @Autowired
    UserHomeDao userHomeDao;

    @Autowired
    UserDao dao;


    @GetMapping("/rentlogin/{homeId}")
    public String showRent(Model model, @PathVariable("homeId") int homeId, HttpSession session, HttpServletRequest request) {
        Home home = homeDao.findByHomeId(homeId);
        if (session.getAttribute("userEmail") != null) {
            model.addAttribute("home45", home);
            model.addAttribute("homeId", home.getHomeId());
            model.addAttribute("currentDate", LocalDate.now());
            LocalDate currentDate = LocalDate.now();
            LocalDate dateThreeMonthsLater = currentDate.plusMonths(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = dateThreeMonthsLater.format(formatter);

            model.addAttribute("dateThreeMonthsLater", formattedDate);

            return "rent";
        } else {
            session.setAttribute("homeID23", home);
            return "rentlogin";
        }
    }


    @PostMapping("/rentlogin/user")
    public String showRent(@RequestParam String email, String password, Model model, HttpSession session) {
        User user = userService.login(email, password);
        if (user == null) {

            return "rentlogin";
        } else {
            Home home = (Home) session.getAttribute("homeID23");
            session.setAttribute("userEmail", user.getUserEmail());
            session.setAttribute("userId", user.getUserId());
            return "redirect:/rentlogin/" + home.getHomeId();

        }
    }



//    @GetMapping("/buylogin/{homeId}")
//    public String showBuy(Model model, @PathVariable("homeId") int homeId, HttpSession session, HttpServletRequest request) {
//        Home home = homeDao.findByHomeId(homeId);
//        System.out.println("homeid"+ home.getHomeId());
//        Payment payment=paymentDao.findById(home.getHomeId()).orElseThrow();
//        if (session.getAttribute("userEmail") != null) {
//            model.addAttribute("payment45",payment);
//            model.addAttribute("home45", home);
//            model.addAttribute("homeId", home.getHomeId());
//            model.addAttribute("currentDate",LocalDate.now());
//            return "buy";
//        } else {
//            session.setAttribute("homeID23",home);
//            return "buylogin";
//        }
//    }

//    llsg
//    @GetMapping("/buylogin/{homeId}")
//    public String showBuy(@PathVariable("homeId") int homeId,HttpSession session,Model model){
//        UserHome userHome=userHomeDao.getUserHomeByHomeId(homeId);
//        Home home=homeDao.findByHomeId(homeId);
//        System.out.println("home"+ home.getPrice());
//        User userHome1=dao.getUserByUserHomeId(userHome.getId());
//        System.out.println("user"+userHome1.getUserEmail());
//        if (session.getAttribute("userEmail") != null) {
//            User user=(User) session.getAttribute("userName") ;
//            model.addAttribute("home", home);
//            model.addAttribute("userName", user.getUserName());
//            model.addAttribute("currentDate",LocalDate.now());
//            return "buy";
//        } else {
//            session.setAttribute("homeID23",userHome);
//            return"buylogin";
//        }
//    }
//
//
//
//
//
//    @PostMapping("/buylogin/user")
//    public String showBuy(@RequestParam String email, String password, Model model, HttpSession session) {
//        User user = userService.login(email, password);
//        if (user == null) {
//
//            return "buylogin";
//        } else {
//            Home home = (Home) session.getAttribute("homeID23");
//            session.setAttribute("userEmail", user.getUserEmail());
//            session.setAttribute("userId", user.getUserId());
//            return "redirect:/buylogin/" + home.getHomeId();
//
//
//        }
//    }


    @GetMapping("/buylogin/{homeId}")
    public String showBuy(@PathVariable("homeId") int homeId, HttpSession session, Model model) {
//        UserHome userHome = userHomeDao.getUserHomeByHomeId(homeId);
        Home home = homeDao.findByHomeId(homeId);

        if (session.getAttribute("userEmail") != null) {
            User user = (User) session.getAttribute("user");
//            session.setAttribute("userName", user.getUserName());
            model.addAttribute("home", home);
            model.addAttribute("user", user);
            model.addAttribute("currentDate", LocalDate.now());
            return "buy";
        } else {
            session.setAttribute("home", home);
            return "buylogin";
        }
    }

    @PostMapping("/buylogin/user")
    public String showBuy(@RequestParam String email, String password, Model model, HttpSession session) {
        User user = userService.login(email, password);

        if (user == null) {
            return "buylogin";
        } else {
//            Home home = (Home) session.getAttribute("home");
//           session.setAttribute("userEmail",user.getUserEmail());
//            model.addAttribute("home", home);
//            session.setAttribute("user", user);
////            model.addAttribute("user",user);
            Home home=(Home)session.getAttribute("home");
            session.setAttribute("userEmail", user.getUserEmail());
            session.setAttribute("userName", user.getUserName());
            return "redirect:/buylogin/" + home.getHomeId();
        }
    }

}
