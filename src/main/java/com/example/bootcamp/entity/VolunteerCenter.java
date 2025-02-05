package com.example.bootcamp.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "volunteer_centers")
public class VolunteerCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "volunteerCenter")
    private List<User> users;


    public long getId() {
        return id;
    }

    //сеттер для id
    public void setId(long id) {
        this.id = id;
    }

    //геттер для name
    public String getName() {
        return name;
    }

    //сеттер для name
    public void setName(String name) {
        this.name = name;
    }

    //геттер для location
    public String getLocation() {
        return location;
    }

    //сеттер для location
    public void setLocation(String location) {
        this.location = location;
    }
}
