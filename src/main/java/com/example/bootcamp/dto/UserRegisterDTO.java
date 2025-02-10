package com.example.bootcamp.dto;


import lombok.Data;

@Data
public class UserRegisterDTO {


    private String name;
    private String username;
    private String password;
    private String mail;
    private String centers;
    private String photo_url;

}
