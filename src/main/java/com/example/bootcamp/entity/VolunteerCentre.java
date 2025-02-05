package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="volunteer")
public class VolunteerCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name="Coordinate_x")
    private float coordinate_x;

    @Column(name="Coordinate_y")
    private float coordinate_y;

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    private List<Person> people;
}
