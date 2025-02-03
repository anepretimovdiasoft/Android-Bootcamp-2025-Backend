package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class OrganizationDTO {
    private long id;
    private String name;
    private String address;
    private float latitude;
    private float longitude;
    private long peopleCount;
}
