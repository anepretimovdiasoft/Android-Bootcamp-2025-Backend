package com.example.bootcamp.service;

import com.example.bootcamp.dto.CentersDTO;
import com.example.bootcamp.dto.FullCentersDTO;
import com.example.bootcamp.dto.UsersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CentersService {
    List<CentersDTO> getAllCenters();
    CentersDTO getCenterById(Long id);
    FullCentersDTO getFullCenterById(Long id);
    CentersDTO createCenter(CentersDTO dto);
    CentersDTO updateCenter(Long id, CentersDTO dto);
    void deleteCenter(Long id);
    Page<CentersDTO> getAllCentersPaginated(Pageable pageable);


    String punishUserToCenter(Long userid, Long centerid);
}
