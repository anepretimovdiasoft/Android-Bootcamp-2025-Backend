package com.example.bootcamp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

//    @Column(name = "password_hash")
//    private String passwordHash;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "role")
    private String role;

//    @ManyToOne
//    @JoinColumn(name = "volunteer_center_id")
//    private VolunteerCenter volunteerCenter;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "volunteer_center_id", referencedColumnName = "id")
    private VolunteerCenter volunteerCenter;

    @OneToMany(mappedBy = "volunteerCenter", cascade = CascadeType.ALL)
    private List<User> users;

    @Column(name = "created_at")
    //    private LocalDateTime createdAt;
    private LocalDateTime createdAt = LocalDateTime.now();
}
