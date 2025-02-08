package com.example.bootcamp.dto;

import com.example.bootcamp.entity.Center;
import lombok.Data;

@Data
public class ProfilesDTO {

    private long id;
    private long centerId;
    private String name;
    private String lastname;
    private String age;
    private String phone;
    private String email;
    private String picture;
    private String bio;
}
