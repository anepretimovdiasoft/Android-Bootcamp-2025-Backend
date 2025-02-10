package com.example.bootcamp.util;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.Volunteer_centers;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CenterMapper {


    public CenterDTO convertToDto(Volunteer_centers centers) {
        return new CenterDTO(centers.getId(), centers.getTitle(), centers.getContacts(), centers.getAddress());
    }
}