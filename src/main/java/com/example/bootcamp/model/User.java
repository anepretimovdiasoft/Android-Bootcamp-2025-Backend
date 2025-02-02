package com.example.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @Email
    @Column(unique = true)
    private String email;

    private String phone;

    @NotBlank
    private String passwordHash;

    private String role;

    private LocalDateTime createdAt;

    // Геттеры и сеттеры
    // Конструкторы
}