package com.example.bootcamp.util;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConvert {
    public static UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPhone_number(user.getPhoneNumber());
        dto.setEmail(user.getEmail());
        dto.setAbout(user.getAbout());
        dto.setPassword(user.getPassword());
        dto.setStatusWork(user.isStatusWork());
        dto.setCenterName(user.getVolunteerCenter().getName());
        return dto;
    }
}