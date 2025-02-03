package com.example.bootcamp.dto;

import java.time.LocalDateTime;

public class VolunteerCenterDTO {
    private Long id;
    private String name;
    private String location;
    private LocalDateTime createdAt;

    // Геттер для id
    public Long getId() {
        return id;
    }

    // Сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    // Геттер для name
    public String getName() {
        return name;
    }

    // Сеттер для name
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для location
    public String getLocation() {
        return location;
    }

    // Сеттер для location
    public void setLocation(String location) {
        this.location = location;
    }

    // Геттер для createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Сеттер для createdAt
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
