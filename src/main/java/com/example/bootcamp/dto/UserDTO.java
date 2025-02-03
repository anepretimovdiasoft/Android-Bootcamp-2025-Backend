package com.example.bootcamp.dto;

import lombok.Data;

import java.text.DecimalFormat;

@Data
public class UserDTO {
    private int role;
    private String name;
    private String email;
    private String password;
    private int phone;
    private String volunteer;
    private String photoUrl;
    private float coordinate_x;
    private float coordinate_y;
}
