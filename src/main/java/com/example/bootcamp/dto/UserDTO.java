package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String phone_number;
    private String email;
    private String about;
    private String password;
    private boolean statusWork;
    private String centerName;
}
