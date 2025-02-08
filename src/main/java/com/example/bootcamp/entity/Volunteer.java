package com.example.bootcamp.entity;

import lombok.Data;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "volunteers")
public class Volunteer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "activestatus")
    private boolean activeStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @ManyToOne
    @JoinColumn(name = "volunteercenter_id", nullable = false)
    private VolunteerCenter volunteerCenter;

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
