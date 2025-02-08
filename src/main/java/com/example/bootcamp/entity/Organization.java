package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lon")
    private Float lon;

    @Column(name = "image_photo")
    private String imagePhoto;

    @Column(name = "info")
    private String info;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<User> users;

}
