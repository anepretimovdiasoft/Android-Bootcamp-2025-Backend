package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class VolunteerRegisterDTO {
    private String name;

    private String username;

    private String email;

    private String password;

    private String volunteerCenter;
}
