package com.example.bootcamp.entity;

import javax.persistence.*;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "latitude", nullable = false)
    Float latitude;

    @Column(name = "longitude", nullable = false)
    Float longitude;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<User> users;
}
