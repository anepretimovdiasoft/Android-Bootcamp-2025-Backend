package com.example.bootcamp.dto.mappers;

import com.example.bootcamp.domain.entity.Center;
import com.example.bootcamp.domain.entity.Event;
import com.example.bootcamp.dto.entity.EventDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EventMapper {
    public static EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setImageLink(event.getImageLink());
        eventDTO.setAddress(event.getAddress());
        eventDTO.setLatitude(event.getLatitude());
        eventDTO.setLongitude(event.getLongitude());
        eventDTO.setCenter(event.getCenter().getId());
        eventDTO.setCenterImageLink(event.getCenter().getLinkLogo());
        eventDTO.setCenterName(event.getCenter().getName());

        return eventDTO;
    }

    public static Event convertFromDTO(EventDTO eventDTO, Center center) {
        Event event = new Event();

        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setImageLink(eventDTO.getImageLink());
        event.setAddress(eventDTO.getAddress());
        event.setLatitude(eventDTO.getLatitude());
        event.setLongitude(eventDTO.getLongitude());
        event.setCenter(center);

        return event;
    }
}
