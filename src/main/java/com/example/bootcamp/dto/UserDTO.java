package com.example.bootcamp.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String birthDate;
    private String name;
    private String description;
    private String avatarUrl;
    private Timestamp joinedAt;
    private Timestamp createdAt;
    private String center;
    private String centerDescription;
    private String authorities;

}
