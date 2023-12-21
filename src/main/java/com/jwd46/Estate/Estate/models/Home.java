package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private int status;
    private boolean active = true;
//    private LocalDateTime dateTime;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
//    private String photo;
    private String photo;

//    @OneToMany(mappedBy = "home", fetch = FetchType.EAGER)
//    private Set<User> users;


    @Transient
    private MultipartFile photoFile;

    // ... other methods and properties ...

    public MultipartFile getPhotoFile() {
        return this.photoFile;
    }

    public void setPhotoFile(MultipartFile photoFile) {
        this.photoFile = photoFile;
    }

//    private byte[] photo;

//    public void setPhoto(String encodeToString) {
//    }
//
//    public void setPhoto(byte[] bytes) {
//    }

    // Other properties...

//        private MultipartFile photo;
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<User> users;

    //    @OneToMany
//    private Payment payment;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public Set<User> getUser() {
        return users;
    }

    public void setHomes(Set<User> users) {
        this.users = users;
    }

    @OneToMany
    private Set<Payment> payment = new HashSet<>();


}
