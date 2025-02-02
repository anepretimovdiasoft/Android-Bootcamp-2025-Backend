package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="name")
    private String name;

    @Column(name="mail")
    private String mail;

    @Column(name="phone")
    private String phone;

    @Column(name="password")
    private String password;


    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private Volunteer_centers centers;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Roles roles;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private Volunteer_status status;
}
