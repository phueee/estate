package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Integer> {

    Payment findByHome(Home home);

}
