package com.example.bootcamp.service;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.dto.VolunteerRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VolunteerService {
    List<VolunteerDTO> getAllVolunteers();

    VolunteerDTO getVolunteerById(Long id);

    VolunteerDTO createVolunteer(VolunteerRegisterDTO dto);

    VolunteerDTO updateVolunteer(Long id, VolunteerDTO dto);

    void deleteVolunteer(Long id);

    VolunteerDTO getVolunteerByUsername(String username);

    Page<VolunteerDTO> getAllVolunteerPaginated(Pageable pageable);

    VolunteerDTO getVolunteerByName(String name);
}
