package com.example.bootcamp.dto;

import com.example.bootcamp.entity.VolunteerCenter;
import lombok.Data;

@Data
public class VolunteerDTO {
    private long id;

    private String name;

    private String email;

    private String password;

    private boolean activeStatus;

    private boolean adminRights;

    private String volunteerCenter;
}
