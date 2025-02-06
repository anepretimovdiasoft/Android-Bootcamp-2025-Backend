package com.example.bootcamp.service;

import com.example.bootcamp.dto.PersonDTO;
import com.example.bootcamp.dto.PersonRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPerson();

    PersonDTO getPersonById(Long id);

    PersonDTO createPerson(PersonRegisterDTO dto);

    PersonDTO updatePerson(Long id, PersonDTO dto);

    void deletePerson(Long id);

    PersonDTO getPersonByUsername(String username);

    Page<PersonDTO> getAllPersonPaginated(Pageable pageable);
}
