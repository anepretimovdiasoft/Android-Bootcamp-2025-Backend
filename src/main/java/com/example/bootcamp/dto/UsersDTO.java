package com.example.bootcamp.dto;

import com.example.bootcamp.modal.Credentials;
import com.example.bootcamp.modal.Profile;
import com.example.bootcamp.modal.Roles;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDTO {

    private long id;
    private Credentials credentials;
    private Roles role;
    private Profile profile;
    private LocalDateTime created;
    private LocalDateTime updated;
}
