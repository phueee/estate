package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.PaymentDao;
import com.jwd46.Estate.Estate.daos.RPaymentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    HomeDao homeDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    RPaymentDao rPaymentDao;
    @Autowired
    UserService userService;


    @GetMapping("/detail")
    public String viewDetail(Model model,HttpServletRequest request) {
        Admin admin= (Admin) request.getSession().getAttribute("admin");
        if(admin != null){
            List<Payment> payments = paymentDao.findAll();
            model.addAttribute("payments", payments);
            return "details";
        }else {
            return "redirect:/";
        }

    }

    @PostMapping("/detail")
    public String viewDetail(Model model, @RequestParam String userName, @PathVariable int userId) {
        User user = userDao.findByUserId(userId);
        Payment payment = new Payment();
        payment.setUser(user);
        paymentDao.save(payment);
        return "redirect:/details";

    }

    @GetMapping("/Rdetail")
    public String viewRDetail(Model model, HttpServletRequest request) {
        Admin admin= (Admin) request.getSession().getAttribute("admin");
        if (admin != null){
            List<RPayment> rPayments = rPaymentDao.findAll();
            model.addAttribute("rPayments", rPayments);
            return "Rdetails";
        }else {
            return "redirect:/";
        }


    }


    @PostMapping("/Rdetail")
    public String viewRdetail(Model model, @RequestParam String userName, @PathVariable int userId) {
        User user = userDao.findByUserId(userId);
        RPayment rPayment = new RPayment();
        rPayment.setUser(user);
        rPaymentDao.save(rPayment);
        return "redirect:/Rdetails";

    }


    @GetMapping("/rentDetail/{homeId}")
    public String showRent(@PathVariable("homeId") int homeId, HttpSession session, Model model) {


        if (session.getAttribute("userEmail") != null) {
            Home home = homeDao.findByHomeId(homeId);
            int userId = (Integer) session.getAttribute("userId");
            User user = userDao.findByUserId(userId);
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

            return "redirect:/login";
        }
    }

    @PostMapping("/rentlogin/user")
    public String showRent(@RequestParam String email, String password, Model model, HttpSession session) {
        User user = userService.login(email, password);

        if (user == null) {
            return "rentlogin";
        } else {
            Home home = (Home) session.getAttribute("home");

            if (home != null) {
                session.setAttribute("userEmail", user.getUserEmail());
                session.setAttribute("userName", user.getUserName());
                return "redirect:/rentlogin/" + home.getHomeId();
            } else {
                return "rentlogin";
            }
        }

    }

    @GetMapping("/buyDetail/{homeId}")
    public String showBuy(@PathVariable("homeId") int homeId, HttpSession session, Model model) {

        if (session.getAttribute("userEmail") != null) {
            Home home = homeDao.findByHomeId(homeId);
            int userId = (Integer) session.getAttribute("userId");
            User user = userDao.findByUserId(userId);
            System.out.println(user.getUserName());
            model.addAttribute("home", home);
            model.addAttribute("user", user);
            model.addAttribute("currentDate", LocalDate.now());
            return "buy";
        } else {
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

    @GetMapping("/payment/{homeId}")
    public String payment(@PathVariable("homeId") int homeId, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        Home home = homeDao.findByHomeId(homeId);
        home.setStatus(0);
        int userId = (Integer) session.getAttribute("userId");
        User user = userDao.findByUserId(userId);
        Payment payment = new Payment();
        payment.setHome(home);
        payment.setUser(user);
        payment.setDateTime(LocalDateTime.now());
        session.setAttribute("homeID",homeId);
        session.setAttribute("userID",userId);

        redirectAttributes.addAttribute("homeId", homeId);
        return "qrcode";
    }


    @PostMapping("/Rpayment/{homeId}")
    public String Rpayment(@PathVariable("homeId") int homeId, @RequestParam("due_date") String due_date,@RequestParam("price")String price, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        Home home = homeDao.findByHomeId(homeId);
        int userId = (Integer) session.getAttribute("userId");
        User user = userDao.findByUserId(userId);
        RPayment rPayment = new RPayment();
        rPayment.setHome(home);
        rPayment.setUser(user);
        System.out.println("HOMEPRICE"+price);
        rPayment.setPrice(price);
        rPayment.setStartDate(LocalDateTime.now());
        try {
            int dueMonths = Integer.parseInt(due_date.replaceAll("\\D", ""));

            LocalDateTime endDate = LocalDateTime.now().plusMonths(dueMonths);
            rPayment.setEndDate(endDate);
            session.setAttribute("homeIDSession",homeId);
            session.setAttribute("RPaymentSession2",userId);
            session.setAttribute("RPaymentSession3",price);
            session.setAttribute("RPaymentSession4",endDate);
            redirectAttributes.addAttribute("homeId", homeId);
            return "qrcode";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid due_date format. Please select a valid option.");
            return "errorPage";
        }




    }





    @PostMapping("/delete/Rdetail")
    public String deleteDetail(@RequestParam int id) {
        rPaymentDao.deleteById(id);
        return "redirect:/Rdetail";

    }


    @PostMapping("/delete/detail")
    public String deleteRDetail(@RequestParam int id) {
        paymentDao.deleteById(id);
        return "redirect:/detail";

    }


}