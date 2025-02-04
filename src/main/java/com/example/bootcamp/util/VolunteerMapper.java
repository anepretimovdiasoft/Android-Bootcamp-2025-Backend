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
        volunteerDTO.setEmail(volunteer.getEmail());
        volunteerDTO.setPassword(volunteer.getPassword());
        volunteerDTO.setActiveStatus(volunteer.isActiveStatus());
        volunteerDTO.setAdminRights(volunteer.isAdminRights());
        volunteerDTO.setVolunteerCenter(volunteer.getVolunteerCenter().getName());
        return volunteerDTO;
    }

    public Volunteer convertToEntity(VolunteerDTO dto){
        Volunteer volunteer = new Volunteer();
        volunteer.setId(dto.getId());
        volunteer.setName(dto.getName());
        volunteer.setEmail(dto.getEmail());
        volunteer.setPassword(dto.getPassword());
        volunteer.setActiveStatus(dto.isActiveStatus());
        volunteer.setAdminRights(dto.isAdminRights());
        return volunteer;
    }
}
