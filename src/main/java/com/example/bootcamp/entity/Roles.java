package com.example.bootcamp.entity;

import javax.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name="roles")
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="role")
    private String role;

    @Override
    public String getAuthority() {
        return "";
    }
}
