package com.example.edu.service;

import com.example.edu.dto.PersonDTO;
import com.example.edu.dto.PersonRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    PersonDTO createPerson(PersonRegisterDto dto);

    PersonDTO getPersonById(Long id);

    PersonDTO getPersonByUsername(String username);

    PersonDTO updatePerson(Long id, PersonDTO dto);

    void deletePerson(Long id);

    List<PersonDTO> getAllPersons();

    Page<PersonDTO> getAllPersonsPaginated(Pageable pageable);
}
