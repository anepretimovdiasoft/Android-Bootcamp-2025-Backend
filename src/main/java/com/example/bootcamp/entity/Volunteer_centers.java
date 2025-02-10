package com.example.bootcamp.entity;

import lombok.Data;
import java.util.List;
import javax.persistence.*;

import javax.persistence.Entity;

@Data
@Entity
@Table(name="volunteer_centers")
public class Volunteer_centers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="address")
    private String address;

    @Column(name="contacts")
    private String contacts;


    @OneToMany(mappedBy = "centers",  cascade = CascadeType.ALL)
    private List<User> users;
}
