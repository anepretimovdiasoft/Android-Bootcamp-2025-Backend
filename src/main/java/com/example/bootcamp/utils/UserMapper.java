package com.example.bootcamp.utils;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setTelegramUsername(user.getTelegramUsername());
        userDTO.setOrganizationName(user.getOrganization().getName());
        userDTO.setAbout(user.getAbout());
        userDTO.setPhotoUrl(user.getPhotoUrl());

        return userDTO;
    }
}
