package com.example.bootcamp.entity;

import liquibase.pro.packaged.C;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "volunteers")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "activestatus")
    private boolean activeStatus;

    @Column(name = "adminrights")
    private boolean adminRights;

    @ManyToOne
    @JoinColumn(name = "volunteercenter_id", nullable = false)
    private VolunteerCenter volunteerCenter;
}
