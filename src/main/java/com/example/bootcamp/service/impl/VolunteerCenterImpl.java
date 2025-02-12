package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.VolunteerCenterDTO;
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
public class VolunteerCenterImpl implements VolunteerCenterService {
    private final VolunteerCenterRepository volunteerCenterRepository;

    @Override
    public VolunteerCenterDTO getVolunteerCenterById(Long id) {
        return volunteerCenterRepository.findById(id).map(VolunteerCenterMapper::convertToDto)
                .orElseThrow(() -> new VolunteerCenterNotFoundException("Volunteer center not found!"));
    }

    @Override
    public List<VolunteerCenterDTO> getAllVolunteerCenter() {
        return volunteerCenterRepository.findAll().stream().map(VolunteerCenterMapper::convertToDto).collect(Collectors.toList());
    }
}
