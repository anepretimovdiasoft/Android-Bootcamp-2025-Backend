package com.example.bootcamp.service;

import com.example.bootcamp.dto.CentersDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CentersService {
    List<CentersDTO> getAllCenters();
    CentersDTO getCenterById(Long id);
    CentersDTO createCenter(CentersDTO dto);
    CentersDTO updateCenter(Long id, CentersDTO dto);
    void deleteCenter(Long id);
}
