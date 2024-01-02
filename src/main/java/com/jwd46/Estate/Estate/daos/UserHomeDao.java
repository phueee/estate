package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.UserHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHomeDao extends JpaRepository<UserHome,Integer> {

    UserHome findByHomeHomeId(int homeId);
}
