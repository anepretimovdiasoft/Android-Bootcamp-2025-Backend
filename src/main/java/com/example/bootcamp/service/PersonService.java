package com.example.bootcamp.service;

import com.example.bootcamp.dto.PersonDTO;
import com.example.bootcamp.dto.PersonGetDTO;
import com.example.bootcamp.dto.PersonRegisterDTO;
import com.example.bootcamp.dto.VolunteerCentreDTO;
import com.example.bootcamp.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPerson();

    List<PersonGetDTO> getAllPersonsName();

    PersonDTO getPersonById(Long id);

    PersonDTO createPerson(PersonRegisterDTO dto);

    PersonDTO updatePerson(Long id, PersonDTO dto);

    void deletePerson(Long id);

    PersonDTO getPersonByUsername(String username);

    Page<PersonDTO> getAllPersonPaginated(Pageable pageable);

    PersonDTO registerAtVolunteerCenter(Long id, String name);

    List<PersonDTO> getAllPersonAtCenter(Long volunteerId);
}
