package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.VolunteerCentreDTO;
import com.example.bootcamp.entity.VolunteerCentre;
import com.example.bootcamp.exception.VolunteerCentreNotFoundException;
import com.example.bootcamp.repository.VolunteerCentreRepository;
import com.example.bootcamp.service.VolunteerCentreService;
import com.example.bootcamp.util.VolunteerCentreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerCentreServiceImpl implements VolunteerCentreService {

    private final VolunteerCentreRepository volunteerCentreRepository;

    @Override
    public List<VolunteerCentreDTO> getAllVolunteerCentre() {
        return volunteerCentreRepository.findAll()
                .stream().map(VolunteerCentreMapper::convertToVolCenDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerCentreDTO getVolunteerCentreById(Long id) {

        return volunteerCentreRepository.findById(id)
                .map(VolunteerCentreMapper::convertToVolCenDTO)
                .orElseThrow(() -> new VolunteerCentreNotFoundException("Volunteer centre not found!"));
    }

    @Override
    public VolunteerCentreDTO updateVolunteerCentre(Long id, VolunteerCentre volunteerCentre) {
        VolunteerCentre volunteerCentreDTO = volunteerCentreRepository.findById(id)
                .orElseThrow(() -> new VolunteerCentreNotFoundException("Volunteer centre not found!"));

        volunteerCentreDTO.setName(volunteerCentre.getName());
        volunteerCentreDTO.setDescription(volunteerCentre.getDescription());
        volunteerCentreDTO.setCoordinate_x(volunteerCentre.getCoordinate_x());
        volunteerCentreDTO.setCoordinate_y(volunteerCentre.getCoordinate_y());

        return VolunteerCentreMapper.convertToVolCenDTO(volunteerCentreRepository.save(volunteerCentreDTO));
    }

    @Override
    public VolunteerCentreDTO createVolunteerCentre(VolunteerCentreDTO volunteerCentre) {

        VolunteerCentre volunteerCentre1 = new VolunteerCentre();
        volunteerCentre1.setName(volunteerCentre.getName());
        volunteerCentre1.setDescription(volunteerCentre.getDescription());
        volunteerCentre1.setCoordinate_x(volunteerCentre.getCoordinate_x());
        volunteerCentre1.setCoordinate_y(volunteerCentre.getCoordinate_y());

        return VolunteerCentreMapper.convertToVolCenDTO(volunteerCentreRepository.save(volunteerCentre1));
    }

    @Override
    public void deleteVolunteerCentre(Long id) {
        volunteerCentreRepository.deleteById(id);
    }
}
