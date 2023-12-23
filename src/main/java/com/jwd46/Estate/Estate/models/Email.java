package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity

@Table(name = "email")
@Data
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String to;
    private String subject;
    private String body;
}
