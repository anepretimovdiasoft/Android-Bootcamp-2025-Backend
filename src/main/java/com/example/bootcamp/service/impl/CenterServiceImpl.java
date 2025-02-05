package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.Center;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.service.CenterService;
import com.example.bootcamp.util.Mapper.CenterMapper;

import com.example.bootcamp.exception.CenterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;


    @Override
    public List<CenterDTO> getAllCenters(Double latitude, Double longitude) {
        List<Center> centers = centerRepository.findAll();
        if (latitude != null & longitude != null) {
            centers = centers.stream().map(center -> CenterMapper.addDistance(center, latitude, longitude, center.getLat(), center.getLng())).collect(Collectors.toList());
        }
        return centers.stream().map(CenterMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CenterDTO> getClosestCenters(double latitude, double longitude) {
        List<Center> centers = centerRepository.findAll().stream().map(center -> CenterMapper.addDistance(center, latitude, longitude, center.getLat(), center.getLng())).collect(Collectors.toList());
        centers = centers.stream().sorted(Comparator.comparingDouble(Center::getDistance))
                .limit(5)
                .collect(Collectors.toList());
        return centers.stream().map(CenterMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CenterDTO getCenterById(Long id, Double latitude, Double longitude) {
        Center center = centerRepository.findById(id).orElseThrow(() -> new CenterNotFoundException("Center with id " + id + " not found"));
        if (latitude != null & longitude != null) {
            center = CenterMapper.addDistance(center, latitude, longitude, center.getLat(), center.getLng());
        }
        return CenterMapper.convertToDTO(center);
    }

    @Override
    public List<UserDTO> getCenterUsers(CenterDTO centerDTO) {
        return centerDTO.getUsers();
    }
}
