package com.jwd46.Estate.Estate.Service;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.PaymentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.Payment;
import com.jwd46.Estate.Estate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentDao pDao;

    private ModelAndView homes;
    private UserDao user;
    @Autowired
    HomeDao dao;

    public Optional<Payment> payment(int id){
        return pDao.findById(id);
    }

    private void create(Payment payment,int userId,int homeId){
        User paymentUser = user.findById(userId).get();
        payment.setUser(paymentUser);

        Home paymentHome=dao.findById(homeId).get();
        payment.setHome(paymentHome);
        pDao.save(payment);
        paymentHome.setStatus(0);
        dao.save(paymentHome);
    }



}
