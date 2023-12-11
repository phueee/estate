package com.jwd46.Estate.Estate.Service;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.RPaymentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.RPayment;
import com.jwd46.Estate.Estate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RPaymentService {
    @Autowired
    RPaymentDao rPaymentDao;

    @Autowired
    UserDao dao;

    @Autowired
    HomeDao homeDao;


    public Optional<RPayment> payment(int id){
        return rPaymentDao.findById(id);
    }


//    private void rentcreate(RPayment rPayment,int userId,int homeId){
//        User rpaymentUser=dao.findById(userId).get();
//        rPayment.setUser(rpaymentUser);
//
//        Home homePayment=dao.findById(homeId).get();
//        rPayment.setHome(homePayment);
//        rPaymentDao.save(rPayment);
//        homePayment.setStatus(0);
//        homeDao.save(homePayment);
//    }

}
