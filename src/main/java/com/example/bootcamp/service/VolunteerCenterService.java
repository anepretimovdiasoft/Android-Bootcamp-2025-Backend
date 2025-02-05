package com.example.bootcamp.service;

import com.example.bootcamp.dto.VolunteerCenterDTO;

import java.util.List;

public interface VolunteerCenterService {
    List<VolunteerCenterDTO> getAllVolunteerCenters();
    VolunteerCenterDTO getVolunteerCenterById(Long id);
    VolunteerCenterDTO createVolunteerCenter(VolunteerCenterDTO dto);
    VolunteerCenterDTO updateVolunteerCenter(Long id, VolunteerCenterDTO dto);
    void deleteVolunteerCenter(Long id);
}
