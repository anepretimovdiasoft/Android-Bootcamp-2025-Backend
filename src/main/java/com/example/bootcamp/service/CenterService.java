package com.example.bootcamp.service;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;

import java.util.List;

public interface CenterService {

    List<CenterDTO> getAllCenters(Double latitude, Double longitude);

    List<CenterDTO> getClosestCenters(double latitude, double longitude);

    CenterDTO getCenterById(Long id, Double latitude, Double longitude);

    List<UserDTO> getCenterUsers(CenterDTO centerDTO);


}
