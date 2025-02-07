package com.example.bootcamp.util;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.entity.Volunteer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VolunteerMapper {

    public VolunteerDTO convertToDto(Volunteer volunteer){
        VolunteerDTO volunteerDTO = new VolunteerDTO();
        volunteerDTO.setId(volunteer.getId());
        volunteerDTO.setName(volunteer.getName());
        volunteerDTO.setUsername(volunteer.getUsername());
        volunteerDTO.setEmail(volunteer.getEmail());
        volunteerDTO.setActiveStatus(volunteer.isActiveStatus());
        volunteerDTO.setVolunteerCenter(volunteer.getVolunteerCenter().getName());
        return volunteerDTO;
    }

}
