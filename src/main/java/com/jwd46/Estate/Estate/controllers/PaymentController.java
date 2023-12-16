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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    RPaymentDao rPaymentDao;
    @Autowired
    UserDao userDao;
    @Autowired
    HomeDao homeDao;

    @GetMapping("payment")
    public String viewPayment(Model model, @PathVariable("userId") int userId, @PathVariable("homeId") int homeId, HttpSession session, Payment payment, HttpServletRequest request){
        model.addAttribute("title","Payment");
        User user=userDao.findByUserId(userId);
        payment.setUser(user);
        Home home=homeDao.findByHomeId(homeId);
        payment.setHome(home);
        model.addAttribute("Payment",new Payment());
        request.getSession().getAttribute("userId");
        paymentDao.save(payment);
                return "payment";
    }





    @GetMapping("/Rpayment")
    public String viewRPayment(Model model, HttpSession session){
        model.addAttribute("User","user");
        return "Rpayment";
    }


//    @PostMapping("/RPayment")
//    public String viewRPayment(@RequestParam("inputDate1") String startDate,
//                               @RequestParam("inputDate2") String endDate,
//                               @RequestParam("inputPayment") String paymentType,
//                               Model model){
//        double paymentResult = calculatePayment(startDate, endDate, paymentType);
//        model.addAttribute("paymentResult", paymentResult);
//    return "Rpayment";
//    }


//    private double calculatePayment(String startDate, String endDate, String paymentType) {
//        return 100.0;
//    }


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



}
