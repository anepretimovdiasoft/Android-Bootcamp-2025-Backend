package com.example.bootcamp.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "center")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @Column(name = "description")
    private String description;

    @Column(name = "distance")
    private Double distance;

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    private List<User> users;

}
