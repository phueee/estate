package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.UserHomeRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHomeRentDao extends JpaRepository<UserHomeRent,Integer> {
}
