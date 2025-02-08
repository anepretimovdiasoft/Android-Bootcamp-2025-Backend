package com.example.edu.service.impl;

import com.example.edu.dto.authorities.AuthorityDTO;
import com.example.edu.dto.person.PersonDTO;
import com.example.edu.dto.person.PersonRegisterDto;
import com.example.edu.dto.person.PersonUpdateDTO;
import com.example.edu.entity.Authority;
import com.example.edu.entity.Department;
import com.example.edu.entity.Person;
import com.example.edu.exception.AlreadyExistsException;
import com.example.edu.exception.BadRequestException;
import com.example.edu.exception.NotFoundException;
import com.example.edu.repository.AuthorityRepository;
import com.example.edu.repository.DepartmentRepository;
import com.example.edu.repository.PersonRepository;
import com.example.edu.service.PersonService;
import com.example.edu.utils.mappers.AuthorityMapper;
import com.example.edu.utils.GlobalUtils;
import com.example.edu.utils.mappers.PersonMapper;
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

    private PersonDTO _updatePerson(@NonNull Person person, @NonNull PersonUpdateDTO dto, boolean partial) {
        if (!partial && GlobalUtils.hasAnyNullField(dto, List.of("departmentId", "departmentName")))
            throw new BadRequestException("All fields must be provided when update is not partial");

        if (!partial && dto.getDepartmentId() == null && dto.getDepartmentName() == null)
            throw new BadRequestException("Update is not partial, so you must specify new department (departmentName or departmentId)");

        personRepository.findByUsername(dto.getUsername()).ifPresent(existingPerson -> {
            if (!existingPerson.getId().equals(person.getId()))
                throw new AlreadyExistsException("Username already exists");
        });

        GlobalUtils.updateIfNotNull(dto.getName(), person::setName);
        GlobalUtils.updateIfNotNull(dto.getUsername(), person::setUsername);
        GlobalUtils.updateIfNotNull(dto.getEmail(), person::setEmail);
        GlobalUtils.updateIfNotNull(dto.getPhotoUrl(), person::setPhotoUrl);
        GlobalUtils.updateIfNotNull(dto.getPhone(), person::setPhone);
        GlobalUtils.updateIfNotNull(dto.getInfo(), person::setInfo);

        if (dto.getDepartmentName() != null) {
            Optional<Department> department = departmentRepository.findByName(dto.getDepartmentName());
            if (department.isEmpty()) throw new NotFoundException("Department was not found");

            person.setDepartment(department.get());
        }

        if (dto.getDepartmentId() != null) {
            Optional<Department> department = departmentRepository.findById(dto.getDepartmentId());
            if (department.isEmpty()) throw new NotFoundException("Department was not found");

            person.setDepartment(department.get());
        }

        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO getPersonByUsername(String username) {
        Optional<Person> userOptional = personRepository.findByUsername(username);

        if (userOptional.isEmpty()) throw new NotFoundException("User was not found");

        return PersonMapper.convertToDTO(userOptional.get());
    }

    @Override
    public PersonDTO createPerson(PersonRegisterDto dto) {
        if (GlobalUtils.hasAnyNullField(dto, null))
            throw new BadRequestException("Register data has empty fields");

        if (personRepository.findByUsername(dto.getUsername()).isPresent())
            throw new AlreadyExistsException("User already exists");

        Optional<Authority> authorityOptional = authorityRepository.findByAuthority("ROLE_USER");
        if (authorityOptional.isEmpty()) throw new RuntimeException("Authority not found");

        Person person = new Person();
        person.setName(dto.getName());
        person.setUsername(dto.getUsername());
        person.setEmail(dto.getEmail());
        person.setPhotoUrl("https://funnyducks.ru/upload/iblock/0cd/0cdeb7ec3ed6fddda0f90fccee05557d.jpg");  // TODO
        person.setPassword(passwordEncoder.encode(dto.getPassword()));
        person.setAuthorities(Set.of(authorityOptional.get()));

        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonUpdateDTO dto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        return this._updatePerson(person, dto, false);
    }

    @Override
    public PersonDTO updatePerson(String username, PersonUpdateDTO dto) {
        Person person = personRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Person not found"));
        return this._updatePerson(person, dto, false);
    }

    @Override
    public PersonDTO patchPerson(Long id, PersonUpdateDTO dto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        return this._updatePerson(person, dto, true);
    }

    @Override
    public PersonDTO patchPerson(String username, PersonUpdateDTO dto) {
        Person person = personRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Person not found"));
        return this._updatePerson(person, dto, true);
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id).map(PersonMapper::convertToDTO).orElseThrow(() -> new NotFoundException("Person not found!"));
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll().stream().map(PersonMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Page<PersonDTO> getAllPersonsPaginated(Pageable pageable) {
        return personRepository.findAll(pageable).map(PersonMapper::convertToDTO);
    }

    @Override
    public List<AuthorityDTO> getAuthorities(Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isEmpty()) throw new NotFoundException("User was not found");
        return AuthorityMapper.convertAllToDTO(person.get().getAuthorities());
    }

    @Override
    public List<AuthorityDTO> getAuthorities(String username) {
        Optional<Person> person = personRepository.findByUsername(username);
        if (person.isEmpty()) throw new NotFoundException("User was not found");
        return AuthorityMapper.convertAllToDTO(person.get().getAuthorities());
    }
}
