package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "volunteercenters")
public class VolunteerCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "centername")
    private String name;

    @Column(name = "coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "volunteerCenter", cascade = CascadeType.ALL)
    private List<Volunteer> volunteer;
}
