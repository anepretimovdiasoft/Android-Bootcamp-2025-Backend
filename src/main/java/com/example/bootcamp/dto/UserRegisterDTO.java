package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
