package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class OrganizationRegisterDTO {
    private String name;
    private String address;
    private float lat;
    private float lon;
    private String imagePhoto;
    private String info;
}
