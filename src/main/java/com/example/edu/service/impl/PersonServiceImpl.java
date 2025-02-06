package com.example.edu.service.impl;

import com.example.edu.dto.PersonDTO;
import com.example.edu.dto.PersonRegisterDto;
import com.example.edu.entity.Authority;
import com.example.edu.entity.Department;
import com.example.edu.entity.Person;
import com.example.edu.exception.DepartmentNotFoundException;
import com.example.edu.exception.PersonAlreadyExistsException;
import com.example.edu.exception.PersonNotFoundException;
import com.example.edu.repository.AuthorityRepository;
import com.example.edu.repository.DepartmentRepository;
import com.example.edu.repository.PersonRepository;
import com.example.edu.service.PersonService;
import com.example.edu.utils.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PersonDTO getPersonByUsername(String username) {
        Optional<Person> userOptional = personRepository.findByUsername(username);

        if (userOptional.isEmpty()) throw new PersonNotFoundException("User with username " + username + " not found");

        return PersonMapper.convertToDTO(userOptional.get());
    }

    @Override
    public PersonDTO createPerson(PersonRegisterDto dto) {
        if (personRepository.findByUsername(dto.getUsername()).isPresent())
            throw new PersonAlreadyExistsException("Username already exists");

        Optional<Department> department = departmentRepository.findByName(dto.getDepartmentName());
        if (department.isEmpty()) {
            //throw new DepartmentNotFoundException("Department not found");
            department = departmentRepository.findById(1L);
        }

        Optional<Authority> authorityOptional = authorityRepository.findByAuthority("ROLE_USER");
        if (authorityOptional.isEmpty()) throw new RuntimeException("Authority not found!");

        Person person = new Person();
        person.setName(dto.getName());
        person.setUsername(dto.getUsername());
        person.setEmail(dto.getEmail());
        person.setDepartment(department.get());
        person.setPassword(passwordEncoder.encode(dto.getPassword()));
        person.setAuthorities(Set.of(authorityOptional.get()));

        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO dto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found!"));

        if (personRepository.findByUsername(dto.getUsername()).isPresent())
            throw new PersonAlreadyExistsException("Username already exists");

        person.setName(dto.getName());
        person.setUsername(dto.getUsername());
        person.setEmail(dto.getEmail());
        person.setPhotoUrl(dto.getPhotoUrl());

        Optional<Department> department = departmentRepository.findByName(dto.getDepartmentName());
        department.ifPresent(person::setDepartment);

        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id)
                .map(PersonMapper::convertToDTO)
                .orElseThrow(() -> new PersonNotFoundException("Person not found!"));
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll().stream()
                .map(PersonMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Page<PersonDTO> getAllPersonsPaginated(Pageable pageable) {
        return personRepository.findAll(pageable).map(PersonMapper::convertToDTO);
    }
}
