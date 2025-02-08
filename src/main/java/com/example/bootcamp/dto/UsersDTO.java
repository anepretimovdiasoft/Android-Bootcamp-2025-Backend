package com.example.bootcamp.dto;

import com.example.bootcamp.entity.Credentials;
import com.example.bootcamp.entity.Profile;
import com.example.bootcamp.entity.Authority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDTO {

    private long id;
    private Credentials credentials;
    private Authority authority;
    private String username;
    private Profile profile;
    private LocalDateTime created;
    private LocalDateTime updated;
}
