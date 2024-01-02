package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.UserHome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHomeDao extends JpaRepository<UserHome,Integer> {

    UserHome findByHomeHomeId(int homeId);
}
