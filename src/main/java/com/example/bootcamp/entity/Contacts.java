package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToMany(mappedBy = "Contacts", cascade = CascadeType.ALL)
    @Column(name = "Id")
    private long id;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "LinkTelegram")
    private String linkTelegram;
}
