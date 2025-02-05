package com.example.bootcamp.modal;

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

    @OneToMany(mappedBy = "credentials")
    private List<Users> users;
}