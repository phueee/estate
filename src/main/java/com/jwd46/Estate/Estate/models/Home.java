package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "homes")
@Data
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int homeId;
    private String homeNo;
    private String bedRoom;
    private String bathRoom;
    private String area;
    private String location;
    private String price;
    private String property;
    private String service;
    private String photo;
}
