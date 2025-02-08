package com.example.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    @JsonIgnore
    private Center center;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private String age;

    @Column(name = "picture")
    private String picture;

    @Column(name = "bio")
    private String bio;

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private List<Users> users;

    @OneToMany(mappedBy = "oldProfile")
    @JsonIgnore
    private List<ProfileUpdateRequest> oldProfileRequests;

    @OneToMany(mappedBy = "newProfile")
    @JsonIgnore
    private List<ProfileUpdateRequest> newProfileRequests;
}