package com.example.bootcamp.service;

import com.example.bootcamp.dto.entity.CenterDTO;

import java.util.List;

public interface CenterService {
    List<CenterDTO> getAll();
    List<CenterDTO> getAllSorted(double userLatitude, double userLongitude);
    CenterDTO getById(long id);

    CenterDTO create(CenterDTO center);
    CenterDTO update(long id, CenterDTO centerDTO);
    void delete(long id);
}
