package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  UserDao extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * FROM users WHERE user_email=?1 AND user_password=?2", nativeQuery = true)
    User findByEmailAndPassword(String email,String password);

   // User findByUserEmailAndUserPassword(String aaa,String vvv);

    List<User> findByActiveTrue();


}
