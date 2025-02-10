package com.example.bootcamp.dto;

import com.example.bootcamp.entity.Credentials;
import com.example.bootcamp.entity.Profile;
import com.example.bootcamp.entity.Authority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDTO {

    private long id;
    private long authorityId;
    private String username;
    private long profileId;
    private LocalDateTime created;
    private LocalDateTime updated;
}
