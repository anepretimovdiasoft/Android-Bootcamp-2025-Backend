package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class PersonRegisterDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private String volunteer;

}
