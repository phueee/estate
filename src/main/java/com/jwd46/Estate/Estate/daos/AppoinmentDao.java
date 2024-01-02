package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoinmentDao extends JpaRepository<Appoinment,Integer> {
        Appoinment findById(int id);
}