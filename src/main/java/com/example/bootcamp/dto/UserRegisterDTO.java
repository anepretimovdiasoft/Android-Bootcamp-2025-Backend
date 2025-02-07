package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
}
