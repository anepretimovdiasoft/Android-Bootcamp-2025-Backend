package com.example.bootcamp.dto;

import lombok.Data;

import java.util.List;

@Data
public class VolunteerCenterDTO {
    private long id;

    private String name;

    private String coordinates;

    private List<Long> volunteer;
}
