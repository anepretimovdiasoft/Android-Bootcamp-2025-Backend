package com.example.bootcamp.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import com.example.bootcamp.entity.VolunteerCenter;

public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String phoneNumber;
//    private String passwordHash;
    private String avatarUrl;
    private String role;
    private String status;
    private Long volunteerCenterId;
//    private LocalDateTime createdAt;
    private VolunteerCenter volunteerCenter; // ??? связь с VolunteerCenter

    // Геттеры и сеттеры
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public String getPasswordHash() {
//        return passwordHash;
//    }
//
//    public void setPasswordHash(String passwordHash) {
//        this.passwordHash = passwordHash;
//    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVolunteerCenterId() {
        return volunteerCenterId;
    }

    public void setVolunteerCenterId(long volunteerCenterId) {
        this.volunteerCenterId = volunteerCenterId;
    }
}
