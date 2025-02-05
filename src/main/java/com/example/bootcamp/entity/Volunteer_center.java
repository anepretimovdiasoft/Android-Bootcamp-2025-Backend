package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "volunteer_center")
public class Volunteer_center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<User> volunteer;
}
