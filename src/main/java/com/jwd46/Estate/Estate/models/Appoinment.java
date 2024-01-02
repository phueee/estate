package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "appoinment")
@Data
public class Appoinment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String reason;
    private String comment;
    private LocalDateTime dateTime;
    private boolean active = true;

    @OneToMany
    private Set<User> user = new HashSet<>();


}