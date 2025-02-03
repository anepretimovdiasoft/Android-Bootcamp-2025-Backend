package com.example.bootcamp.modal;

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
    private List<Users> users;

    @OneToMany(mappedBy = "oldProfile")
    private List<ProfileUpdateRequest> oldProfileRequests;

    @OneToMany(mappedBy = "newProfile")
    private List<ProfileUpdateRequest> newProfileRequests;
}