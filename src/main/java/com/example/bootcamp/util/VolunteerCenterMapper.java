package com.example.bootcamp.util;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.entity.VolunteerCenter;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

public class VolunteerCenterMapper {

    public static VolunteerCenterDTO convertToDto(VolunteerCenter volunteerCenter) {
        VolunteerCenterDTO dto = new VolunteerCenterDTO();
        dto.setId(volunteerCenter.getId());
        dto.setName(volunteerCenter.getName());
        dto.setLocation(volunteerCenter.getLocation());
        return dto;
    }

    public static VolunteerCenter convertToEntity(VolunteerCenterDTO volunteerCenterDTO) {
        VolunteerCenter volunteerCenter = new VolunteerCenter();
        volunteerCenter.setId(volunteerCenterDTO.getId());
        volunteerCenter.setName(volunteerCenterDTO.getName());
        volunteerCenter.setLocation(volunteerCenterDTO.getLocation());
        return volunteerCenter;
    }
}
