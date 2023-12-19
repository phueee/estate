package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private int userId;
//    private int homeId;
//    private String homeNo;
//    private String userName;
    private String price;
    private String date;
    private String payment;
//    @OneToOne
//    private User user;
//    @OneToOne
//    private Home home;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "home_id", referencedColumnName = "homeId")
    private Home home;
}
