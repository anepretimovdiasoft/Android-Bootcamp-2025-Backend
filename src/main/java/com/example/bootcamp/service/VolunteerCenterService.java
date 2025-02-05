package com.example.bootcamp.service;

import com.example.bootcamp.dto.VolunteerCenterDTO;

import java.util.List;

public interface VolunteerCenterService {
    List<VolunteerCenterDTO> getAllVolunteerCenter();

    VolunteerCenterDTO getVolunteerCenterById(Long id);

//    List<VolunteerDTO> getVolunteerByVolunteerCenter(VolunteerCenterDTO dto)

    VolunteerCenterDTO createVolunteerCenter(VolunteerCenterDTO dto);

    VolunteerCenterDTO updateVolunteerCenter(Long id, VolunteerCenterDTO dto);

    void deleteVolunteerCenter(Long id);
}
