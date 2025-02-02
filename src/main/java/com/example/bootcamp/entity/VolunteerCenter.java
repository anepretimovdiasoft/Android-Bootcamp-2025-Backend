package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "volunteer_centers")
public class VolunteerCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "about")
    private String about;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "longitude")
    private float longitude;
    @OneToMany(mappedBy = "volunteerCenter", cascade = CascadeType.ALL)
    private List<User> volunteers;
}
