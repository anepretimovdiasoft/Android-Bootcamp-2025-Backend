package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String name;
    private String secondName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String organizationName;
    private String email;
    private String telegramLink;
    private String info;
}
