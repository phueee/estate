package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_nrc")
    private String userNrc;

    @Column(name = "user_password")
    private String userPassword;


    }
