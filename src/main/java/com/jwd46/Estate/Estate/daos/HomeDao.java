package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeDao extends JpaRepository<Home,Integer> {

    List<Home> findByActiveTrue();

    List<Home> findAllByProperty(String name);

    List<Home> findByService(String name);

//    @Query(value = "SELECT * FROM home WHERE homeId=?1", nativeQuery = true)
//    Home findById(int homeId );

    Home findByHomeId(int homeId);
}
