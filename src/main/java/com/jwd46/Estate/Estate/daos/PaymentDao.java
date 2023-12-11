package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Integer> {
//
//    @Query("SELECT p FROM Payment p JOIN FETCH p.user JOIN FETCH p.home WHERE p.id = :paymentId")
//    Optional<Payment> findPaymentWithUserAndHomeById(int paymentId);
}
