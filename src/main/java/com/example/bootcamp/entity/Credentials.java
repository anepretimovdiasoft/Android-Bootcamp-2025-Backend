package com.example.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "credentials")
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "credentials")
    @JsonIgnore
    private List<Users> users;
}