package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.StatusesDTO;
import com.example.bootcamp.modal.Status;
import com.example.bootcamp.repository.StatusRepository;
import com.example.bootcamp.service.StatusesService;
import com.example.bootcamp.util.StatusesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusesServiceImpl implements StatusesService {

    private final StatusRepository statusesRepository;

    @Autowired
    public StatusesServiceImpl(StatusRepository statusesRepository) {
        this.statusesRepository = statusesRepository;
    }

    @Override
    public List<StatusesDTO> getAllStatuses() {
        return statusesRepository.findAll().stream()
                .map(StatusesMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StatusesDTO getStatusById(Long id) {
        Optional<Status> status = statusesRepository.findById(id);
        return status.map(StatusesMapper::convertDTO).orElse(null);
    }

    @Override
    public StatusesDTO createStatus(StatusesDTO dto) {
        Status status = new Status();
        status.setStatus(dto.getStatus());

        Status savedStatus = statusesRepository.save(status);

        StatusesDTO responseDto = new StatusesDTO();
        responseDto.setStatus(savedStatus.getStatus());

        return responseDto;
    }


    @Override
    public StatusesDTO updateStatus(Long id, StatusesDTO dto) {
        Optional<Status> existingStatusOptional = statusesRepository.findById(id);
        if (existingStatusOptional.isPresent()) {
            Status existingStatus = existingStatusOptional.get();
            existingStatus.setStatus(dto.getStatus());
            Status updatedStatus = statusesRepository.save(existingStatus);
            return StatusesMapper.convertDTO(updatedStatus);
        }
        return null;
    }

    @Override
    public void deleteStatus(Long id) {
        statusesRepository.deleteById(id);
    }
}
