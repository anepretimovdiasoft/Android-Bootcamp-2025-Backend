package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormat;

@Data
@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Role")
    private int role;

    @Column(name="Name")
    private String name;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="Phone")
    private int phone;

    @ManyToOne
    @JoinColumn(name="volunteer_centre_id")
    private VolunteerCentre volunteer;

    @Column(name="Coordinate_x")
    private float coordinate_x;

    @Column(name="Coordinate_y")
    private float coordinate_y;

    @Column(name="Photo_url")
    private String photoUrl;
}
