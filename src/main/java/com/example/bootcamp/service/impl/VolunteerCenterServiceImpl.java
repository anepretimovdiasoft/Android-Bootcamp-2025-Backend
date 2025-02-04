package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exceptions.VolunteerCenterExistException;
import com.example.bootcamp.exceptions.VolunteerCenterNotFoundException;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import com.example.bootcamp.repository.VolunteerRepository;
import com.example.bootcamp.service.VolunteerCenterService;
import com.example.bootcamp.util.VolunteerCenterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerCenterServiceImpl implements VolunteerCenterService {

    private final VolunteerCenterRepository volunteerCenterRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public List<VolunteerCenterDTO> getAllVolunteerCenter() {
        return volunteerCenterRepository.findAll()
                .stream()
                .map(VolunteerCenterMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerCenterDTO getVolunteerCenterById(Long id) {
        return volunteerCenterRepository.findById(id)
                .map(VolunteerCenterMapper::convertToDto)
                .orElseThrow(() -> new VolunteerCenterNotFoundException("Volunteer center not found!"));
    }

//    @Override
//    public List<VolunteerDTO> getVolunteerByVolunteerCenter(VolunteerCenterDTO dto) {
//        return dto.getVolunteer();
//    }

    @Override
    public VolunteerCenterDTO createVolunteerCenter(VolunteerCenterDTO dto) {

        Optional<VolunteerCenter> optionalVolunteerCenter = volunteerCenterRepository.findByName(dto.getName());
        if(optionalVolunteerCenter.isEmpty()){
            VolunteerCenter volunteerCenter = VolunteerCenterMapper.convertToEntity(dto);
            volunteerCenter.setVolunteer(volunteerRepository.findAllById(dto.getVolunteer()));
            return VolunteerCenterMapper.convertToDto(volunteerCenterRepository.save(volunteerCenter));
        } else{
            throw new VolunteerCenterExistException("This volunteer center already exists!");
        }
    }

    @Override
    public VolunteerCenterDTO updateVolunteerCenter(Long id, VolunteerCenterDTO dto) {
        VolunteerCenter volunteerCenter = volunteerCenterRepository.findById(id)
                .orElseThrow(() -> new VolunteerCenterNotFoundException("Volunteer center not found!"));

        volunteerCenter.setName(dto.getName());
        volunteerCenter.setCoordinates(dto.getCoordinates());

        volunteerCenter.setVolunteer(volunteerRepository.findAllById(dto.getVolunteer()));

        return VolunteerCenterMapper.convertToDto(volunteerCenterRepository.save(volunteerCenter));
    }

    @Override
    public void deleteVolunteerCenter(Long id) {
        volunteerCenterRepository.deleteById(id);
    }
}
