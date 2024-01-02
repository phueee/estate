package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity

@Table(name = "appoinment")
@Data
public class Appoinment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String reason;
    private String comment;
    private LocalDateTime dateTime;

}