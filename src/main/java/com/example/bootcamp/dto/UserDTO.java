package com.example.bootcamp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private Date birthDate;
    private String phoneNumber;
    private String telegramUsername;
    private String organizationName;
    private String about;
    private String photoUrl;
}
