package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
    @Table(name = "Rpayments")
    @Data
    public class RPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "home_id", referencedColumnName = "homeId")
    private Home home;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dueDate;
    private String basePrice;
    private String price;


    }

