package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.PaymentDao;
import com.jwd46.Estate.Estate.daos.RPaymentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.Payment;
import com.jwd46.Estate.Estate.models.RPayment;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("payment")
//    public String viewPayment(Model model, @PathVariable("userId") int userId, @PathVariable("homeId") int homeId, HttpSession session, Payment payment, HttpServletRequest request){
//        model.addAttribute("title","Payment");
//        User user=userDao.findByUserId(userId);
//        payment.setUser(user);
//        Home home=homeDao.findByHomeId(homeId);
//        payment.setHome(home);
//        model.addAttribute("Payment",new Payment());
//        request.getSession().getAttribute("userId");
//        paymentDao.save(payment);
//        return "payment";
//    }

    @GetMapping("Rpayment")
    public String viewRPayment(Model model){
        model.addAttribute("title","RPayment");
        model.addAttribute("User", new User());
        return "Rpayment";
    }

    @GetMapping("/detail")
    public String viewDetail(Model model){
        List<Payment> payments=paymentDao.findAll();
        model.addAttribute("payments",payments);
        return "details";
    }

    @PostMapping("/detail")
    public String viewDetail(Model model,@RequestParam String userName, @PathVariable("userId") int userId){
        User user=userDao.findByUserId(userId);
        Payment payment=new Payment();
        payment.setUser(user);
        paymentDao.save(payment);
        return "redirect:/details";

    }

    @GetMapping("/Rdetail")
    public String viewRDetail(Model model){
        List<RPayment> rPayments=rPaymentDao.findAll();
        model.addAttribute("rPayments",rPayments);
        return "Rdetails";
    }


    @PostMapping("/Rdetail")
    public String viewRdetail(Model model,@RequestParam String userName,@PathVariable("userId") int userId){
        User user=userDao.findByUserId(userId);
        RPayment rpayment=new RPayment();
        rpayment.setUser(user);
        rPaymentDao.save(rpayment);
        return "redirect:/Rdetails";

    }


//    @PostMapping("/payment")
//    public String viewPayment(Model model, @PathVariable("userId") int userId, @PathVariable("homeId") int homeId, HttpSession session, Payment payment, HttpServletRequest request){
//        model.addAttribute("title","Payment");
//        User user=userDao.findByUserId(userId);
//        payment.setUser(user);
//        Home home=homeDao.findByHomeId(homeId);
//        payment.setHome(home);
//        model.addAttribute("Payment",new Payment());
//        request.getSession().getAttribute("userId");
//        paymentDao.save(payment);
//        return "payment";
//    }


//    @GetMapping("/payment/{userId}/{homeId}")
//    public String showPayment(Model model,@PathVariable("userId") int userId,@PathVariable("homeId") int homeId,HttpSession session){
//        System.out.println(homeId);
//        int id = Integer.parseInt("homeId");
//        System.out.println(id);
//        System.out.print("This is output here");
//        Home home=homeDao.findByHomeId(homeId);
//        session.setAttribute("home45",home);
//        System.out.println(userId);
//        User user=userDao.findByUserId(userId);
//        session.setAttribute("user12",user);
//        return "payment";
//    }

    @PostMapping("/payment")
    public String paymentView(HttpServletRequest request,Model model,HttpSession session){
//        request.getSession().setAttribute("home",home);
//        model.addAttribute("home",home);
        String sessionUserId = session.getAttribute("userId").toString();
        int userId = Integer.valueOf(sessionUserId);
        User user=userDao.findByUserId(userId);
//        model.addAttribute("user",user);
        session.setAttribute("userId",user);
        Home home = homeDao.findByHomeId(1);
        return "redirect:/paymentForm";
    }

    @GetMapping("/paymentForm")
    public String directToPaymentForm(HttpServletRequest request, HttpSession session,Model model) {

        session.getAttribute("userId");
        return "payment";
    }



//
//    @PostMapping("/payment")
//    public String paymentView(Model model, HttpSession session, @ModelAttribute("Payment") Payment payment, HttpServletRequest request){
//        Home home=(Home) session.getAttribute("home45");

//        model.addAttribute("homeId",home.getHomeId());
//        model.addAttribute("homeNo",home.getHomeNo());
//        model.addAttribute("price",home.getPrice());
//        User user=(User) session.getAttribute("user12");
//        model.addAttribute("user", user);
//        model.addAttribute("userId",user.getUserId());
//        model.addAttribute("name",user.getUserName());
//        payment.setUser(user);
//        payment.setHome(home);
//        request.getSession().getAttribute("userId");
//        model.addAttribute("Payment",new Payment());
//        paymentDao.save(payment);
//        return "redirect:/index" ;
//    }




}
