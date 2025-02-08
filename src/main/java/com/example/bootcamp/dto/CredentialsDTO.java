package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class CredentialsDTO {

    private long id;
    private String login;
    private String hashedPassword;
    private String name;
    private String lastname;
}
