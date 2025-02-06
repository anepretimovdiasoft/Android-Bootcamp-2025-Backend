package com.example.bootcamp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_role")
public class User_role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
