package com.example.bootcamp.entity;

import javax.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Authority> authorities;

    @Column(name = "birth_date")
    Date birthDate;

    @Column(name = "phone_number", unique = true)
    String phoneNumber;

    @Column(name = "telegram_username", unique = true)
    String telegramUsername;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    Organization organization;

    @Column(name = "about")
    String about;

    @Column(name = "photo_url")
    String photoUrl;

    @Override
    public String getUsername() {
        return this.getEmail();
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
