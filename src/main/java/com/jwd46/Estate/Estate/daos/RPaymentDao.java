package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.RPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPaymentDao extends JpaRepository<RPayment,Integer> {

    RPayment findByHome(Home home);
}
