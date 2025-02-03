package com.example.bootcamp.util;

import com.example.bootcamp.dto.VolunteerCentreDTO;
import com.example.bootcamp.entity.VolunteerCentre;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VolunteerCentreMapper {
    public VolunteerCentreDTO convertToVolCenDTO(VolunteerCentre volunteerCentre){

        VolunteerCentreDTO volunteerCentreDTO = new VolunteerCentreDTO();
        volunteerCentreDTO.setName(volunteerCentre.getName());
        volunteerCentreDTO.setDescription(volunteerCentre.getDescription());
        volunteerCentreDTO.setCoordinate_x(volunteerCentre.getCoordinate_x());
        volunteerCentreDTO.setCoordinate_y(volunteerCentre.getCoordinate_y());

        return volunteerCentreDTO;

    }


}
