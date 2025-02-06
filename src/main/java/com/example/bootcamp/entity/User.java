package com.example.bootcamp.entity;



import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "active")
    private boolean active;


    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "photo_url")
    private String photo_url;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @ManyToOne
    @JoinColumn(name = "center_id", nullable = true)
    private Volunteer_centers centers;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;




    public boolean getActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId(){ return id;}

    public void setId(Long id){this.id = id; }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities()");
        return roles;

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
