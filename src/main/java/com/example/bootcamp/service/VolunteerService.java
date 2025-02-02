package com.example.bootcamp.service;

import com.example.bootcamp.dto.entity.volunteer.VolunteerDTO;
import com.example.bootcamp.dto.entity.volunteer.VolunteerRegisterDTO;

import java.util.List;

public interface VolunteerService {
    List<VolunteerDTO> getAll();
    VolunteerDTO getById(long id);
    VolunteerDTO getByEmail(String email);
    VolunteerDTO getByTelephone(String telephone);

    VolunteerDTO create(VolunteerRegisterDTO volunteerRegisterDTO);
    VolunteerDTO update(long id, VolunteerDTO volunteerDTO);
    void delete(long volunteerId);

    List<VolunteerDTO> free();
}
