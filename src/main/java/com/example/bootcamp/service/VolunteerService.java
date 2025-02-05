package com.example.bootcamp.service;

import com.example.bootcamp.dto.VolunteerDTO;

import java.util.List;

public interface VolunteerService {
    List<VolunteerDTO> getAllVolunteers();

    VolunteerDTO getVolunteerById(Long id);

    VolunteerDTO createVolunteer(VolunteerDTO dto);

    VolunteerDTO updateVolunteer(Long id, VolunteerDTO dto);

    void deleteVolunteer(Long id);
}
