package com.example.bootcamp.service;

import com.example.bootcamp.dto.StatusesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusesService {
    List<StatusesDTO> getAllStatuses();
    StatusesDTO getStatusById(Long id);
    StatusesDTO createStatus(StatusesDTO dto);
    StatusesDTO updateStatus(Long id, StatusesDTO dto);
    void deleteStatus(Long id);
}
