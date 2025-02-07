package com.example.edu.dto.person;

import lombok.Data;

@Data
public class PersonDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String photoUrl;
    private String info;
    private String phone;
    private String departmentName;
}
