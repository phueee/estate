package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "homes")
@Data

public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int homeId;
    @Column(name = "home_No")
    private String homeNo;
    private String bedRoom;
    private String bathRoom;
    private String area;
    private String location;
    private String price;
    private String property;
    private String service;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;
}
