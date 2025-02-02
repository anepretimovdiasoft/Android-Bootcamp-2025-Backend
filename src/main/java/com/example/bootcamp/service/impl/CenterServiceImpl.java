package com.example.bootcamp.service.impl;

import com.example.bootcamp.aspect.annotation.LogExample;
import com.example.bootcamp.domain.entity.Center;
import com.example.bootcamp.domain.entity.Event;
import com.example.bootcamp.domain.entity.Volunteer;
import com.example.bootcamp.domain.exception.ResourceNotFoundException;
import com.example.bootcamp.dto.entity.CenterDTO;
import com.example.bootcamp.dto.mappers.CenterMapper;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.EventRepository;
import com.example.bootcamp.repository.VolunteerRepository;
import com.example.bootcamp.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;
    private final EventRepository eventRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    @LogExample
    public List<CenterDTO> getAll() {
        return centerRepository.findAll().stream()
                .map(CenterMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public List<CenterDTO> getAllSorted(double userLatitude, double userLongitude) {
        return centerRepository.findAll().stream()
                .map(CenterMapper::convertToDTO)
                .sorted(createComparator(new Point2D.Double(userLatitude, userLongitude)))
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public CenterDTO getById(long id) {
        return CenterMapper.convertToDTO(centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Центр с id (" + id + ") не найден!")));
    }

    @Override
    @LogExample
    public CenterDTO create(CenterDTO center) {
        List<Volunteer> volunteers = volunteerRepository.findAllById(center.getVolunteers());
        List<Event> events = eventRepository.findAllById(center.getEvents());

        return CenterMapper.convertToDTO(centerRepository.save(CenterMapper.convertFromDTO(center, volunteers, events)));
    }

    @Override
    @LogExample
    public CenterDTO update(long id, CenterDTO centerDTO) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Центр с таким (" + id + ") не найден!"));

        center.setName(centerDTO.getName());
        center.setDescription(centerDTO.getDescription());
        center.setAddress(centerDTO.getAddress());
        center.setLinkLogo(centerDTO.getLinkLogo());
        center.setLatitude(centerDTO.getLatitude());
        center.setLongitude(centerDTO.getLongitude());
        center.setVkLink(centerDTO.getVkLink());
        center.setLinkSite(centerDTO.getLinkSite());

        return CenterMapper.convertToDTO(centerRepository.save(center));
    }

    @Override
    @LogExample
    public void delete(long id) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Центр с таким (" + id + ") не найден!"));

        // Уничтожение связей волонтеров с центром.
        // todo: Наверно это можно реализовать через CascadeType какой-нибудь
        volunteerRepository.saveAll(center.getVolunteers().stream()
                .peek(volunteer -> volunteer.setCenter(null))
                .collect(Collectors.toList()));

        centerRepository.deleteById(id);
    }

    private static Comparator<CenterDTO> createComparator(Point2D point) {
        return (c0, c1) -> {
            Point2D point0 = new Point2D.Double(c0.getLatitude(), c0.getLongitude());
            Point2D point1 = new Point2D.Double(c1.getLatitude(), c1.getLongitude());
            return Double.compare(point0.distanceSq(point), point1.distanceSq(point));
        };
    }
}
