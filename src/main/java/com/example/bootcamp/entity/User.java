package com.example.bootcamp.entity;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {
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

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;


    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
