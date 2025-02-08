package com.example.bootcamp.service;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CenterService {
    List<CenterDTO> getAllCentres();

    List<UserDTO> getUsersByCenter(Long id);

    List<UserDTO> getUsersByCenter(String name);

    CenterDTO getCenterById(Long id);

    CenterDTO getCenterByName(String name);

    List<CenterDTO> getCenterByLocation(UserDTO user);

    Page<CenterDTO> getAllCentresPaginated(Pageable pageable);

}
