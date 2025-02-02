package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "about")
    private String about;
    @Column(name = "password")
    private String password;
    @Column(name = "status_work")
    private boolean statusWork;
    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private VolunteerCenter volunteerCenter;
}
