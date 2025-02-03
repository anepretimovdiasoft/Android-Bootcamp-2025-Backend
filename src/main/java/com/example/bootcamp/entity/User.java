package com.example.bootcamp.entity;

import javax.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Role role;

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


}
