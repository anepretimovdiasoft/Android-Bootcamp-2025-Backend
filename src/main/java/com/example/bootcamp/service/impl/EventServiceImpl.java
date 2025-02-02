package com.example.bootcamp.service.impl;

import com.example.bootcamp.aspect.annotation.LogExample;
import com.example.bootcamp.domain.entity.Center;
import com.example.bootcamp.domain.entity.Event;
import com.example.bootcamp.domain.exception.ResourceNotFoundException;
import com.example.bootcamp.dto.entity.EventDTO;
import com.example.bootcamp.dto.mappers.EventMapper;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.EventRepository;
import com.example.bootcamp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CenterRepository centerRepository;


    @Override
    @LogExample
    public List<EventDTO> getAll() {
        return eventRepository.findAll().stream()
                .map(EventMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public List<EventDTO> getAllSorted(double userLatitude, double userLongitude) {
        return eventRepository.findAll().stream()
                .map(EventMapper::convertToDTO)
                .sorted(createComparator(new Point2D.Double(userLatitude, userLongitude)))
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public EventDTO getById(long id) {
        return eventRepository.findById(id)
                .map(EventMapper::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Ивент с id (" + id + ") не найден!"));
    }

    @Override
    @LogExample
    public EventDTO create(EventDTO eventDTO) {
        Center center = centerRepository.findById(eventDTO.getCenter())
                .orElseThrow(() -> new ResourceNotFoundException("Центр с таким (" + eventDTO.getCenter() + ") не найден!"));

        return EventMapper.convertToDTO(eventRepository.save(EventMapper.convertFromDTO(eventDTO, center)));
    }

    @Override
    @LogExample
    public EventDTO update(long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ивент с id (" + id + ") не найден!"));

        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Центр с таким (" + id + ") не найден!"));

        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setImageLink(eventDTO.getImageLink());
        event.setAddress(eventDTO.getAddress());
        event.setLatitude(eventDTO.getLatitude());
        event.setLongitude(eventDTO.getLongitude());
        event.setCenter(center);

        return EventMapper.convertToDTO(eventRepository.save(event));
    }

    @Override
    @LogExample
    public void delete(long id) {
        eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ивент с id (" + id + ") не найден!"));
        eventRepository.deleteById(id);
    }

    private static Comparator<EventDTO> createComparator(Point2D point) {
        return (e0, e1) -> {
            Point2D point0 = new Point2D.Double(e0.getLatitude(), e0.getLongitude());
            Point2D point1 = new Point2D.Double(e1.getLatitude(), e1.getLongitude());
            return Double.compare(point0.distanceSq(point), point1.distanceSq(point));
        };
    }
}
