package com.example.bootcamp.dto;

import com.example.bootcamp.modal.Center;
import lombok.Data;

@Data
public class ProfilesDTO {

    private long id;
    private Center centerId;
    private String name;
    private String lastname;
    private String age;
    private String picture;
    private String bio;
}
