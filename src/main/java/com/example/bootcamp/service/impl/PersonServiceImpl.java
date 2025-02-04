package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.PersonDTO;
import com.example.bootcamp.dto.PersonRegisterDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.entity.Person;
import com.example.bootcamp.entity.VolunteerCentre;
import com.example.bootcamp.exception.PersonAlreadyExistException;
import com.example.bootcamp.exception.PersonNotFoundException;
import com.example.bootcamp.exception.VolunteerCentreNotFoundException;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.repository.PersonRepository;
import com.example.bootcamp.repository.VolunteerCentreRepository;
import com.example.bootcamp.service.PersonService;
import com.example.bootcamp.util.PersonMapper;
import lombok.RequiredArgsConstructor;
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
    private final VolunteerCentreRepository volunteerCentreRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<PersonDTO> getAllPerson() {
        return personRepository.findAll().stream()
                .map(PersonMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id)
                .map(PersonMapper::convertToDto)
                .orElseThrow(() -> new PersonNotFoundException("User not found!"));
    }

    @Override
    public PersonDTO createPerson(PersonRegisterDTO dto) {
        if(personRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new PersonAlreadyExistException("Username already exists");
        }

        Optional<VolunteerCentre> optionalVolunteer = volunteerCentreRepository.findByName(dto.getVolunteer());
        if (optionalVolunteer.isEmpty()) {
            throw new VolunteerCentreNotFoundException("Volunteer Centre not found");
        }

        Optional<Authority> roleUser = authorityRepository.findByAuthority("ROLE_USER");
        if(roleUser.isEmpty()) throw new RuntimeException("Authority not found");

        Person person = new Person();
        person.setName(dto.getName());
        person.setUsername(dto.getUsername());
        person.setEmail(dto.getEmail());
        person.setVolunteer(optionalVolunteer.get());
        person.setPassword(passwordEncoder.encode(dto.getPassword()));
        person.setAuthorities(Set.of(roleUser.get()));

        return PersonMapper.convertToDto(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("User not found!"));

        if(personRepository.findByUsername(dto.getUsername()).isPresent())
            throw new PersonAlreadyExistException("Username already exist");

        person.setName(dto.getName());
        person.setUsername(person.getUsername());
        person.setEmail(dto.getEmail());
        person.setPhone(dto.getPhone());
        person.setPhotoUrl(dto.getPhotoUrl());

        Optional<VolunteerCentre> optionalVolunteerCentre = volunteerCentreRepository.findByName(dto.getVolunteer());
        optionalVolunteerCentre.ifPresent(person::setVolunteer);

        return PersonMapper.convertToDto(personRepository.save(person));
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public PersonDTO getPersonByUsername(String username) {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);

        if(optionalPerson.isEmpty())
            throw new PersonNotFoundException("User with username: " + username + " not found");

        return PersonMapper.convertToDto(optionalPerson.get());
    }
}
