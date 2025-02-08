package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class VolunteerDTO {
    private long id;

    private String name;

    private String username;

    private String email;

    private boolean activeStatus;

    private String volunteerCenter;
}
