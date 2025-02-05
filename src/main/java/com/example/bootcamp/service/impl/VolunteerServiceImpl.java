package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.entity.Volunteer;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exceptions.VolunteerCenterNotFoundException;
import com.example.bootcamp.exceptions.VolunteerNotFoundException;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import com.example.bootcamp.repository.VolunteerRepository;
import com.example.bootcamp.service.VolunteerService;
import com.example.bootcamp.util.VolunteerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final VolunteerCenterRepository volunteerCenterRepository;

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
    public VolunteerDTO createVolunteer(VolunteerDTO dto) {
        Optional<VolunteerCenter> optionalVolunteerCenter = volunteerCenterRepository.findByName(dto.getVolunteerCenter());
        if(optionalVolunteerCenter.isEmpty()){
            throw new VolunteerCenterNotFoundException("Volunteer center not found!");
        }

        Volunteer volunteer = VolunteerMapper.convertToEntity(dto);
        volunteer.setVolunteerCenter(optionalVolunteerCenter.get());

        return VolunteerMapper.convertToDto(volunteerRepository.save(volunteer));
    }

    @Override
    public VolunteerDTO updateVolunteer(Long id, VolunteerDTO dto) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found!"));

        volunteer.setName(dto.getName());
        volunteer.setEmail(dto.getEmail());
        volunteer.setPassword(dto.getPassword());
        volunteer.setActiveStatus(dto.isActiveStatus());
        volunteer.setAdminRights(dto.isAdminRights());

        Optional<VolunteerCenter>  optionalVolunteerCenter = volunteerCenterRepository.findByName(dto.getVolunteerCenter());
        optionalVolunteerCenter.ifPresent(volunteer::setVolunteerCenter);

        return VolunteerMapper.convertToDto((volunteerRepository.save(volunteer)));
    }

    @Override
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}
