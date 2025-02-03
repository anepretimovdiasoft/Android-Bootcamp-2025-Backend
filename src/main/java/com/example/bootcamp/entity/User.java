package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "contact_info")
    private String contact_info;

    @Column(name = "biography")
    private String biography;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "status")
    private Volunteer_center status;
}
