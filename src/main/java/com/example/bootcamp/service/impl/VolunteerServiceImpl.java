package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.dto.VolunteerRegisterDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.entity.Volunteer;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exceptions.AuthorityNotFoundException;
import com.example.bootcamp.exceptions.VolunteerAlreadyExistsException;
import com.example.bootcamp.exceptions.VolunteerCenterNotFoundException;
import com.example.bootcamp.exceptions.VolunteerNotFoundException;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import com.example.bootcamp.repository.VolunteerRepository;
import com.example.bootcamp.service.VolunteerService;
import com.example.bootcamp.util.VolunteerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final VolunteerCenterRepository volunteerCenterRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<VolunteerDTO> getAllVolunteers() {
        return volunteerRepository.findAll()
                .stream()
                .map(VolunteerMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerDTO getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .map(VolunteerMapper::convertToDto)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found!"));
    }

    @Override
    public VolunteerDTO createVolunteer(VolunteerRegisterDTO dto) {

        if(volunteerRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new VolunteerAlreadyExistsException("Username "+dto.getUsername()+" already exists");
        }

        Optional<VolunteerCenter> optionalVolunteerCenter = volunteerCenterRepository.findByName(dto.getVolunteerCenter());
        if(optionalVolunteerCenter.isEmpty()){
            throw new VolunteerCenterNotFoundException("Volunteer center not found!");
        }

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority("ROLE_VOLUNTEER");
        if(optionalAuthority.isEmpty()) throw new AuthorityNotFoundException("Authority not found");

        Volunteer volunteer = new Volunteer();
        volunteer.setName(dto.getName());
        volunteer.setUsername(dto.getUsername());
        volunteer.setEmail(dto.getEmail());
        volunteer.setVolunteerCenter(optionalVolunteerCenter.get());
        volunteer.setPassword(passwordEncoder.encode(dto.getPassword()));
        volunteer.setAuthorities(Set.of(optionalAuthority.get()));
        volunteer.setActiveStatus(false);

        return VolunteerMapper.convertToDto(volunteerRepository.save(volunteer));
    }

    @Override
    public VolunteerDTO updateVolunteer(Long id, VolunteerDTO dto) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found!"));

        if(volunteerRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new VolunteerAlreadyExistsException("Username already exists!");
        }

        volunteer.setName(dto.getName());
        volunteer.setUsername(dto.getUsername());
        volunteer.setEmail(dto.getEmail());
        volunteer.setActiveStatus(dto.isActiveStatus());

        Optional<VolunteerCenter>  optionalVolunteerCenter = volunteerCenterRepository.findByName(dto.getVolunteerCenter());
        optionalVolunteerCenter.ifPresent(volunteer::setVolunteerCenter);

        return VolunteerMapper.convertToDto((volunteerRepository.save(volunteer)));
    }

    @Override
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }

    @Override
    public VolunteerDTO getVolunteerByUserName(String username) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findByUsername(username);

        if(optionalVolunteer.isEmpty()){
            throw new VolunteerNotFoundException("User with username "+username+" not found");
        }

        return VolunteerMapper.convertToDto(optionalVolunteer.get());
    }
}
