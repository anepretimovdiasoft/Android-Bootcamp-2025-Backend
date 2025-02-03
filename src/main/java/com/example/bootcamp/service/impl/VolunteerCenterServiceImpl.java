package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exception.VolunteerCenterNotFoundException;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import com.example.bootcamp.service.VolunteerCenterService;
import com.example.bootcamp.util.VolunteerCenterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerCenterServiceImpl implements VolunteerCenterService {

    private final VolunteerCenterRepository volunteerCenterRepository;

    @Override
    public List<VolunteerCenterDTO> getAllVolunteerCenters() {
        return volunteerCenterRepository.findAll().stream()
                .map(VolunteerCenterMapper::convertToDto)  // стат вызов
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerCenterDTO getVolunteerCenterById(Long id) {
        return volunteerCenterRepository.findById(id)
                .map(VolunteerCenterMapper::convertToDto)  // стат вызов
                .orElseThrow(() -> new VolunteerCenterNotFoundException("Volunteer Center not found!"));
    }

    @Override
    public VolunteerCenterDTO createVolunteerCenter(VolunteerCenterDTO volunteerCenterDTO) {
        VolunteerCenter volunteerCenter = VolunteerCenterMapper.convertToEntity(volunteerCenterDTO);  // стат вызов
        return VolunteerCenterMapper.convertToDto(volunteerCenterRepository.save(volunteerCenter));  // стат вызов
    }

    @Override
    public VolunteerCenterDTO updateVolunteerCenter(Long id, VolunteerCenterDTO volunteerCenterDTO) {
        VolunteerCenter volunteerCenter = volunteerCenterRepository.findById(id)
                .orElseThrow(() -> new VolunteerCenterNotFoundException("Volunteer Center not found!"));

        volunteerCenter.setName(volunteerCenterDTO.getName());
        volunteerCenter.setLocation(volunteerCenterDTO.getLocation());

        return VolunteerCenterMapper.convertToDto(volunteerCenterRepository.save(volunteerCenter));  // стат вызов
    }

    @Override
    public void deleteVolunteerCenter(Long id) {
        volunteerCenterRepository.deleteById(id);
    }
}
