package com.example.bootcamp.service;

import com.example.bootcamp.dto.entity.EventDTO;

import java.util.List;

public interface EventService {
    // Сортировка ближайших по расстоянию
    List<EventDTO> getAll();
    List<EventDTO> getAllSorted(double userLatitude, double userLongitude);
    EventDTO getById(long id);

    EventDTO create(EventDTO eventDTO);
    EventDTO update(long id, EventDTO eventDTO);
    void delete(long id);
}
