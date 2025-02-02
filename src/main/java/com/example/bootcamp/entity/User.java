package com.example.bootcamp.entity;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String description;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "joined_at")
    private Timestamp joinedAt;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "admin_rights")
    private boolean adminRights;

}
