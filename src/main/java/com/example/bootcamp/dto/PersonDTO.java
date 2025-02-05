package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class PersonDTO {
    private String name;
    private String username;
    private String email;
    private int phone;
    private String volunteer;
    private String photoUrl;
    private float coordinate_x;
    private float coordinate_y;
}
