package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User> findByUserPasswordAndUserEmail(String email,String password);
}
