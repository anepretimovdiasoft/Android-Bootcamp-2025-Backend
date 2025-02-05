package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exception.CenterNotFoundException;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.CenterService;
import com.example.bootcamp.util.ConverterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;
    private final UserRepository userRepository;

    @Override
    public List<CenterDTO> getAllCentres() {
        return centerRepository.findAll().stream()
                .map(ConverterDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByCenter(Long id) {
        VolunteerCenter center = centerRepository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException("Invalid Center ID"));

        return center.getVolunteers().stream()
                .map(ConverterDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByCenter(String name) {
        VolunteerCenter center = centerRepository.findByName(name)
                .orElseThrow(() -> new CenterNotFoundException("Invalid Center name"));

        return center.getVolunteers().stream()
                .map(ConverterDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CenterDTO getCenterById(Long id) {
        VolunteerCenter center = centerRepository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException("Invalid Center ID"));

        return ConverterDTO.convertToDTO(center);
    }

    @Override
    public CenterDTO getCenterByName(String name) {
        VolunteerCenter center = centerRepository.findByName(name)
                .orElseThrow(() -> new CenterNotFoundException("Invalid Center name"));

        return ConverterDTO.convertToDTO(center);
    }

    //////////////////
    @Override
    public List<CenterDTO> getCenterByLocation(UserDTO user) {
        return List.of();
    }
}
