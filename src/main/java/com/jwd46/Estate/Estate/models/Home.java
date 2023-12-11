package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "homes")
@Data
@Getter
@Setter
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
    private int status;
    private boolean active = true;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

//    @OneToMany
//    private Payment payment;

}