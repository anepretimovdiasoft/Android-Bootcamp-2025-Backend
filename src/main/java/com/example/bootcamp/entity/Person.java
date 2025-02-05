package com.example.bootcamp.entity;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="person")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="Name")
    private String name;

    @Column(name = "Username")
    private String username;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="Phone")
    private int phone;

    @ManyToOne
    @JoinColumn(name="volunteer_centre_id")
    private VolunteerCentre volunteer;

    @Column(name="Coordinate_x")
    private float coordinate_x;

    @Column(name="Coordinate_y")
    private float coordinate_y;

    @Column(name="Photo_url")
    private String photoUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

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
