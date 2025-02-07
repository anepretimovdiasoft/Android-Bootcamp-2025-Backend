package com.example.edu.service.impl;

import com.example.edu.dto.person.PersonDTO;
import com.example.edu.dto.person.PersonRegisterDto;
import com.example.edu.dto.person.PersonUpdateDTO;
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
import com.example.edu.utils.GlobalUtils;
import com.example.edu.utils.PersonMapper;
import lombok.NonNull;
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
        if (department.isEmpty()) department = departmentRepository.findById(1L);
        if (department.isEmpty()) throw new DepartmentNotFoundException("Unable to create person: bad department specified");

        Optional<Authority> authorityOptional = authorityRepository.findByAuthority("ROLE_USER");
        if (authorityOptional.isEmpty()) throw new RuntimeException("Authority not found");

        Person person = new Person();
        person.setName(dto.getName());
        person.setUsername(dto.getUsername());
        person.setEmail(dto.getEmail());
        person.setPhotoUrl("https://funnyducks.ru/upload/iblock/0cd/0cdeb7ec3ed6fddda0f90fccee05557d.jpg");  // TODO
        person.setDepartment(department.get());
        person.setPassword(passwordEncoder.encode(dto.getPassword()));
        person.setAuthorities(Set.of(authorityOptional.get()));

        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    private PersonDTO _updatePerson(@NonNull Person person, @NonNull PersonUpdateDTO dto, boolean partial) {
        if (!partial && GlobalUtils.hasAnyNullField(dto)) throw new IllegalArgumentException("All fields must be provided when partial=false");

        personRepository.findByUsername(dto.getUsername()).ifPresent(existingPerson -> {
            if (!existingPerson.getId().equals(person.getId())) throw new PersonAlreadyExistsException("Username already exists");
        });

        GlobalUtils.updateIfNotNull(dto.getName(), person::setName);
        GlobalUtils.updateIfNotNull(dto.getUsername(), person::setUsername);
        GlobalUtils.updateIfNotNull(dto.getEmail(), person::setEmail);
        GlobalUtils.updateIfNotNull(dto.getPhotoUrl(), person::setPhotoUrl);
        GlobalUtils.updateIfNotNull(dto.getPhone(), person::setPhone);
        GlobalUtils.updateIfNotNull(dto.getInfo(), person::setInfo);

        if (dto.getDepartmentName() != null || !partial) departmentRepository.findByName(dto.getDepartmentName()).ifPresent(person::setDepartment);
        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonUpdateDTO dto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return this._updatePerson(person, dto, false);
    }

    @Override
    public PersonDTO updatePerson(String username, PersonUpdateDTO dto) {
        Person person = personRepository.findByUsername(username).orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return this._updatePerson(person, dto, false);
    }

    @Override
    public PersonDTO patchPerson(Long id, PersonUpdateDTO dto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return this._updatePerson(person, dto, true);
    }

    @Override
    public PersonDTO patchPerson(String username, PersonUpdateDTO dto) {
        Person person = personRepository.findByUsername(username).orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return this._updatePerson(person, dto, true);
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
