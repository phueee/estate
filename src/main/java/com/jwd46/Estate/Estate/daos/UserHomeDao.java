package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.UserHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHomeDao extends JpaRepository<UserHome,Integer>{


   @Query(value = "SELECT uh.* FROM userhome uh INNER JOIN homes h ON h.home_id=uh.home_id WHERE h.home_id=?1", nativeQuery = true)
    UserHome getUserHomeByHomeId(int homeId);
}
