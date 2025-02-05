package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import com.example.bootcamp.service.VolunteerCenterService;
import com.example.bootcamp.util.VolunteerCenterMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerCenterServiceImpl implements VolunteerCenterService {

    private final VolunteerCenterRepository volunteerCenterRepository;

    public VolunteerCenterServiceImpl(VolunteerCenterRepository volunteerCenterRepository) {
        this.volunteerCenterRepository = volunteerCenterRepository;
    }

    @Override
    public List<VolunteerCenterDTO> getAllVolunteerCenters() {
        return volunteerCenterRepository.findAll().stream()
                .map(VolunteerCenterMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerCenterDTO getVolunteerCenterById(Long id) {
        return volunteerCenterRepository.findById(id)
                .map(VolunteerCenterMapper::convertToDto)
                .orElseThrow(() -> new RuntimeException("Volunteer Center not found!"));
    }

    @Override
    public VolunteerCenterDTO createVolunteerCenter(VolunteerCenterDTO dto) {
        VolunteerCenter volunteerCenter = VolunteerCenterMapper.convertToEntity(dto);
        return VolunteerCenterMapper.convertToDto(volunteerCenterRepository.save(volunteerCenter));
    }

    @Override
    public VolunteerCenterDTO updateVolunteerCenter(Long id, VolunteerCenterDTO dto) {
        VolunteerCenter volunteerCenter = volunteerCenterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer Center not found!"));
        volunteerCenter.setName(dto.getName());
        volunteerCenter.setLocation(dto.getLocation());
        return VolunteerCenterMapper.convertToDto(volunteerCenterRepository.save(volunteerCenter));
    }

    @Override
    public void deleteVolunteerCenter(Long id) {
        volunteerCenterRepository.deleteById(id);
    }
}
