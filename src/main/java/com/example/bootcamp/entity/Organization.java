package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "VolunteersCenters")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageId")
    private long imageId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Info")
    private String info;

//    @OneToMany(mappedBy = "VolunteersCenters", cascade = CascadeType.ALL)
//    private List<User> users;
}
