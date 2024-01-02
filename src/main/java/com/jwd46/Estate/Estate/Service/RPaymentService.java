package com.jwd46.Estate.Estate.Service;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RPaymentService {


    @Autowired
    UserDao dao;

    @Autowired
    HomeDao homeDao;


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
