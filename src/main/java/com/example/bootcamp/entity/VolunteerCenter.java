package com.example.bootcamp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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
//    private LocalDateTime createdAt;
    private LocalDateTime createdAt = LocalDateTime.now();

}

