package com.example.bootcamp.dto.mappers;

import com.example.bootcamp.domain.entity.Center;
import com.example.bootcamp.domain.entity.Event;
import com.example.bootcamp.domain.entity.Volunteer;
import com.example.bootcamp.dto.entity.CenterDTO;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CenterMapper {
    public static CenterDTO convertToDTO(Center center) {
        CenterDTO centerDTO = new CenterDTO();

        centerDTO.setId(center.getId());
        centerDTO.setName(center.getName());
        centerDTO.setDescription(center.getDescription());
        centerDTO.setAddress(center.getAddress());
        centerDTO.setLinkLogo(center.getLinkLogo());
        centerDTO.setLatitude(center.getLatitude());
        centerDTO.setLongitude(center.getLongitude());
        centerDTO.setVkLink(center.getVkLink());
        centerDTO.setLinkSite(center.getLinkSite());
        centerDTO.setVolunteers(center.getVolunteers().stream().map(Volunteer::getId).collect(Collectors.toList()));
        centerDTO.setEvents(center.getEvents().stream().map(Event::getId).collect(Collectors.toList()));

        return centerDTO;
    }

    public static Center convertFromDTO(CenterDTO centerDTO, List<Volunteer> volunteers, List<Event> events) {
        Center center = new Center();

        center.setName(centerDTO.getName());
        center.setDescription(centerDTO.getDescription());
        center.setAddress(centerDTO.getAddress());
        center.setLinkLogo(centerDTO.getLinkLogo());
        center.setLatitude(centerDTO.getLatitude());
        center.setLongitude(centerDTO.getLongitude());
        center.setVkLink(centerDTO.getVkLink());
        center.setLinkSite(centerDTO.getLinkSite());
        center.setVolunteers(volunteers);
        center.setEvents(events);

        return center;
    }
}
