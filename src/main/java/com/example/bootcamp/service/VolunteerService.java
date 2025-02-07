package com.example.bootcamp.service;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.dto.VolunteerRegisterDTO;

import java.util.List;

public interface VolunteerService {
    List<VolunteerDTO> getAllVolunteers();

    VolunteerDTO getVolunteerById(Long id);

    VolunteerDTO createVolunteer(VolunteerRegisterDTO dto);

    VolunteerDTO updateVolunteer(Long id, VolunteerDTO dto);

    void deleteVolunteer(Long id);

    VolunteerDTO getVolunteerByUserName(String username);
}
