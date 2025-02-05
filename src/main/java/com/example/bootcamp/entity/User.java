package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "RoleId")
    private long roleId;

    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private String password;

    @Column(name = "Name")
    private String firstName;

    @Column(name = "SecondName")
    private String secondName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Info")
    private String info;
}
