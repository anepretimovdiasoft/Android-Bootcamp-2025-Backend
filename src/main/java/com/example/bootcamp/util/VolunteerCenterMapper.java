package com.example.bootcamp.util;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.entity.Volunteer;
import com.example.bootcamp.entity.VolunteerCenter;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class VolunteerCenterMapper{

    public VolunteerCenterDTO convertToDto(VolunteerCenter volunteerCenter){

        VolunteerCenterDTO volunteerCenterDTO = new VolunteerCenterDTO();
        List<Volunteer> listVolunteer = volunteerCenter.getVolunteer();
        List<Long> listIdVolunteer = new ArrayList<>();

        volunteerCenterDTO.setId(volunteerCenter.getId());
        volunteerCenterDTO.setName(volunteerCenter.getName());
        volunteerCenterDTO.setCoordinates(volunteerCenter.getCoordinates());

        for(Volunteer volunteer : listVolunteer){
            listIdVolunteer.add(volunteer.getId());
        }
        volunteerCenterDTO.setVolunteer(listIdVolunteer);

        return volunteerCenterDTO;
    }

    public VolunteerCenter convertToEntity(VolunteerCenterDTO dto){

        VolunteerCenter volunteerCenter = new VolunteerCenter();

        volunteerCenter.setId(dto.getId());
        volunteerCenter.setName(dto.getName());
        volunteerCenter.setCoordinates(dto.getCoordinates());

        return volunteerCenter;
    }
}
