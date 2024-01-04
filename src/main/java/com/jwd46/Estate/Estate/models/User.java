package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
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

//    @NotNull(message = "Name must not be Empty!")
    @Column(name = "user_name")
    private String userName;

//    @NotNull(message = "Email must not be Empty!")
    @Column(name = "user_email")
    private String userEmail;

//    @NotNull(message = "Phone must not be Empty!")
    @Column(name = "user_phone")
    private String userPhone;

//    @NotNull(message = "NRC must not be Empty!")
    @Column(name = "user_nrc")
    private String userNrc;

//    @Size(min = 8, max = 30, message = "Password must be between 8 to 30 characters!")
    @Column(name = "user_password")
    private String userPassword;

//    @Column(name = "confirm_password")
//    @Size(min = 8, max = 30, message = "Password must be between 8 to 30 characters!")
//    private String confirmPassword;

    @Transient
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



    }
