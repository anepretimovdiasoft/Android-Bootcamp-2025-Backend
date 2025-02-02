package com.example.bootcamp.entity;

import liquibase.pro.packaged.C;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="volunteer_status")
public class Volunteer_status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="isbusy")
    private boolean isbusy;

    @OneToOne(mappedBy = "status", cascade = CascadeType.ALL)
    private User user;


}
