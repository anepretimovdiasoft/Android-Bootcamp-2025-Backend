package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String contactInfo;
    private String biography;
    private String photo;
}
