package com.example.edu.dto.person;

import lombok.Data;

@Data
public class PersonRegisterDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String departmentName;
}
