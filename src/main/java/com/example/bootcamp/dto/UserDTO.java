package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String contactInfo;
    private String biography;
    private String photo;
    private Long status;
}
