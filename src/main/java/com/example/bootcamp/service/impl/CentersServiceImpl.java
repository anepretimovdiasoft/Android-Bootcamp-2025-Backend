package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.CentersDTO;
import com.example.bootcamp.entity.Center;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.service.CentersService;
import com.example.bootcamp.util.CentersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CentersServiceImpl implements CentersService {

    private final CenterRepository centerRepository;

    @Override
    public List<CentersDTO> getAllCenters() {
        return centerRepository.findAll().stream()
                .map(CentersMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CentersDTO getCenterById(Long id) {
        Optional<Center> center = centerRepository.findById(id);
        return center.map(CentersMapper::convertDTO).orElse(null);
    }

    @Override
    public CentersDTO createCenter(CentersDTO dto) {
        Center center = new Center();
        center.setName(dto.getName());
        center.setAddress(dto.getAddress());
        center.setLatitude(dto.getLatitude());
        center.setLongitude(dto.getLongitude());

        Center savedCenter = centerRepository.save(center);
        return CentersMapper.convertDTO(savedCenter);
    }


    @Override
    public CentersDTO updateCenter(Long id, CentersDTO dto) {
        Optional<Center> existingCenterOptional = centerRepository.findById(id);
        if (existingCenterOptional.isPresent()) {
            Center existingCenter = existingCenterOptional.get();
            existingCenter.setName(dto.getName());
            existingCenter.setAddress(dto.getAddress());
            existingCenter.setLatitude(dto.getLatitude());
            existingCenter.setLongitude(dto.getLongitude());
            Center updatedCenter = centerRepository.save(existingCenter);
            return CentersMapper.convertDTO(updatedCenter);
        }
        return null;
    }

    @Override
    public void deleteCenter(Long id) {
        centerRepository.deleteById(id);
    }
}
