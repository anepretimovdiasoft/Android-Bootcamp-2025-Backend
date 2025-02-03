package com.example.bootcamp.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "volunteer_centers")
public class VolunteerCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactInfo;
    private LocalDateTime createdAt;

    // Геттеры и сеттеры
    // Конструкторы
}