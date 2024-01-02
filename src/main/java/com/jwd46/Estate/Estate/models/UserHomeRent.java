package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "userHomeRent")
@Data
public class UserHomeRent {

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
}
