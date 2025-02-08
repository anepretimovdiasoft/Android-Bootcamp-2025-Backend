package com.example.bootcamp.service;

import com.example.bootcamp.dto.PersonDTO;
import com.example.bootcamp.dto.VolunteerCentreDTO;
import com.example.bootcamp.entity.Person;
import com.example.bootcamp.entity.VolunteerCentre;
import com.example.bootcamp.repository.VolunteerCentreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VolunteerCentreService {
    List<VolunteerCentreDTO> getAllVolunteerCentre();

    VolunteerCentreDTO getVolunteerCentreById(Long id);

    VolunteerCentreDTO updateVolunteerCentre(Long id, VolunteerCentreDTO dto);

    VolunteerCentreDTO createVolunteerCentre(VolunteerCentreDTO volunteerCentre);

    void deleteVolunteerCentre(Long id);
}
