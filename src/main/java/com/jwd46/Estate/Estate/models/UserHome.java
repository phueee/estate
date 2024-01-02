package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "userhome")
@Data
public class UserHome {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "home_id", referencedColumnName = "homeId")
    private Home home;
}
