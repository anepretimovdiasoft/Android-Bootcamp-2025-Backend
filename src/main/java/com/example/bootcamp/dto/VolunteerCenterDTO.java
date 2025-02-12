package com.example.bootcamp.dto;

import com.example.bootcamp.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class VolunteerCenterDTO {
    public Long id;
    public String address;
    public Double latitude;
    public Double longitude;
    public String photo;
    public String description;
    public List<User> volunteer;
}
