package com.example.edu.service;

import com.example.edu.dto.authorities.AuthorityDTO;
import com.example.edu.dto.person.PersonDTO;
import com.example.edu.dto.person.PersonRegisterDto;
import com.example.edu.dto.person.PersonUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    PersonDTO createPerson(PersonRegisterDto dto);

    PersonDTO getPersonById(Long id);

    PersonDTO getPersonByUsername(String username);

    PersonDTO updatePerson(Long id, PersonUpdateDTO dto);

    PersonDTO updatePerson(String username, PersonUpdateDTO dto);

    PersonDTO patchPerson(Long id, PersonUpdateDTO dto);

    PersonDTO patchPerson(String username, PersonUpdateDTO dto);

    void deletePerson(Long id);

    List<PersonDTO> getAllPersons();

    Page<PersonDTO> getAllPersonsPaginated(Pageable pageable);

    Page<PersonDTO> getByDepartmentNamePaginated(String departmentName, Pageable pageable);

    List<AuthorityDTO> getAuthorities(Long personId);

    List<AuthorityDTO> getAuthorities(String username);
}
