package com.example.bootcamp.util;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.entity.VolunteerCenter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VolunteerCenterMapper {

    //статический метод для конвертации Entity в DTO
    public static VolunteerCenterDTO convertToDto(VolunteerCenter volunteerCenter) {
        if (volunteerCenter == null) {
            return null;
        }

        VolunteerCenterDTO dto = new VolunteerCenterDTO();
        dto.setId(volunteerCenter.getId());
        dto.setName(volunteerCenter.getName());
        dto.setLocation(volunteerCenter.getLocation());
        return dto;
    }

    //статический метод для конвертации DTO в Entity
    public static VolunteerCenter convertToEntity(VolunteerCenterDTO volunteerCenterDTO) {
        if (volunteerCenterDTO == null) {
            return null;
        }

        VolunteerCenter volunteerCenter = new VolunteerCenter();
        volunteerCenter.setId(volunteerCenterDTO.getId());
        volunteerCenter.setName(volunteerCenterDTO.getName());
        volunteerCenter.setLocation(volunteerCenterDTO.getLocation());
        return volunteerCenter;
    }
}
