package com.jwd46.Estate.Estate.daos;

import com.jwd46.Estate.Estate.models.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeDao extends JpaRepository<Home,Integer> {
}
