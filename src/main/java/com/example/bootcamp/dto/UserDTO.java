package com.example.bootcamp.dto;


import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String name;
    private String mail;
    private String phone;
    private String centers;
    private String roles;
    private boolean status;

}

