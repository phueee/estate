package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
    @Table(name = "Rpayments")
    @Data
    public class RPayment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String amount;
        private String startdate;
        private String enddate;
        private String payment;
        @ManyToOne
        private User user;


    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> users=new HashSet<>();

        @OneToOne
        private Home home;

    }
