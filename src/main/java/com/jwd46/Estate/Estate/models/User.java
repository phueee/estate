package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_nrc")
    private String userNrc;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "confirm_password")
    private String confirmPassword;

    private boolean active = true;

//    @Lob
//    @Column(name = "photo", columnDefinition = "LONGBLOB")
//    private String photo;
//    @Transient
//    private MultipartFile photoFile;
//    public MultipartFile getPhotoFile() {
//        return this.photoFile;
//    }
//    public void setPhotoFile(MultipartFile photoFile) {
//        this.photoFile = photoFile;
//    }

    @ManyToMany
    @JoinTable(
            name = "user_home",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "home_id")
    )
    private List<Home> homes1;


    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Home> homes=new HashSet<>();

    public Set <Home>getHome(){
        return homes;
    }

    public void setHomes(Set<Home>homes){
        this.homes=homes;
    }

    @OneToMany
    private Set<Payment> payment = new HashSet<>();

    }
