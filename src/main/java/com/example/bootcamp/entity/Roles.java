package com.example.bootcamp.entity;

import javax.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="role")
    private String role;

    @OneToOne(mappedBy = "roles", cascade = CascadeType.ALL)
    private User user;
}
