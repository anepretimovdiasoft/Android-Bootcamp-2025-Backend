package com.example.bootcamp.service;

import com.example.bootcamp.dto.VolunteerCenterDTO;

import java.util.List;

public interface VolunteerCenterService {
    List<VolunteerCenterDTO> getAllVolunteerCenters();
    VolunteerCenterDTO getVolunteerCenterById(Long id);
    VolunteerCenterDTO createVolunteerCenter(VolunteerCenterDTO volunteerCenterDTO);
    VolunteerCenterDTO updateVolunteerCenter(Long id, VolunteerCenterDTO volunteerCenterDTO);
    void deleteVolunteerCenter(Long id);
}
