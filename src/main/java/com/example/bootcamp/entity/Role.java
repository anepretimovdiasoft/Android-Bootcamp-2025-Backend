package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Roles")
public class Role {
    @Id
    @Column(name = "Id")
    private long id;

    @Column(name = "Status")
    private String status;
}
