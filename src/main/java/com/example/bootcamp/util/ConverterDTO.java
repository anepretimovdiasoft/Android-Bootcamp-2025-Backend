package com.example.bootcamp.util;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.VolunteerCenter;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class ConverterDTO {
    public static UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setPhone_number(user.getPhoneNumber());
        dto.setEmail(user.getEmail());
        dto.setAbout(user.getAbout());
        dto.setPassword(user.getPassword());
        dto.setStatusWork(user.isStatusWork());
        dto.setCenterName(user.getVolunteerCenter().getName());
        return dto;
    }

    public static CenterDTO convertToDTO(VolunteerCenter center) {
        CenterDTO dto = new CenterDTO();
        dto.setId(center.getId());
        dto.setName(center.getName());
        dto.setAddress(center.getAddress());
        dto.setPhoneNumber(center.getPhoneNumber());
        dto.setAbout(center.getAbout());
        dto.setLatitude(center.getLatitude());
        dto.setLongitude(center.getLongitude());
        dto.setVolunteersID(center.getVolunteers().stream().map(User::getId).collect(Collectors.toList()));
        return dto;
    }
}