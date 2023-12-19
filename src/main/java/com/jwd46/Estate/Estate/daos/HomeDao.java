package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface HomeDao extends JpaRepository<Home,Integer> {

    List<Home> findByActiveTrue();

    List<Home> findAllByProperty(String name);

    List<Home> findByService(String name);

//    @Query(value = "SELECT * FROM homes WHERE property=?1 AND location=?2" , nativeQuery = true)
//    Home findByPropertyTypeAndLocation(String property,String location);

    List<Home> findByPropertyAndLocation(String property,String location);
    List<Home> findByProperty(String property);
    List<Home> findByLocation(String location);


    Home findByHomeId(int homeId);

    List<Home> findAllByService(String name);

//    void save(Home home, MultipartFile photoFile);
//    Home save(Home home);
}
