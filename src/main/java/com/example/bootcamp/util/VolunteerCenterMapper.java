package com.example.bootcamp.util;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.entity.VolunteerCenter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VolunteerCenterMapper {
    public VolunteerCenterDTO convertToDto(VolunteerCenter volunteerCenter){
        VolunteerCenterDTO dto = new VolunteerCenterDTO();
        dto.setId(volunteerCenter.getId());
        dto.setAddress(volunteerCenter.getAddress());
        dto.setLatitude(volunteerCenter.getLatitude());
        dto.setLongitude(volunteerCenter.getLongitude());
        dto.setPhoto(volunteerCenter.getPhoto());
        dto.setDescription(volunteerCenter.getDescription());
        dto.setVolunteer(volunteerCenter.getVolunteer());

        return dto;
    }

}
