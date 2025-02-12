package com.example.bootcamp.service;

import com.example.bootcamp.dto.VolunteerCenterDTO;

import java.util.List;

public interface VolunteerCenterService {
    VolunteerCenterDTO getVolunteerCenterById(Long id);

    List<VolunteerCenterDTO> getAllVolunteerCenter();
}
