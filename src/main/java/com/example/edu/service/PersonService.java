package com.example.edu.service;

import com.example.edu.dto.PersonDTO;
import com.example.edu.dto.PersonRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPersons();

    PersonDTO getPersonById(Long id);

    PersonDTO getPersonByUsername(String username);

    PersonDTO createPerson(PersonRegisterDto dto);

    PersonDTO updatePerson(Long id, PersonDTO dto);

    void deletePerson(Long id);

    Page<PersonDTO> getAllPersonsPaginated(Pageable pageable);
}
