package com.example.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Roles")
public class Role {
    @Id
//    @OneToOne
//    @JoinColumn(name = "Id", nullable = false)
    private long id;

    @Column(name = "Status")
    private String status;
}
