package com.example.bootcamp.dto;

import com.example.bootcamp.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class CenterDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String about;
    private float latitude;
    private float longitude;
    //private List<User> volunteers;
}
