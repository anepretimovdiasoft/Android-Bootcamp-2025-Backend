package com.example.edu.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pathToImage")
    private String pathToImage;

    @Column(name = "address")
    private String address;

    @Column(name = "information")
    private String information;

    @Column(name = "latLng")
    private String latLng;
}
