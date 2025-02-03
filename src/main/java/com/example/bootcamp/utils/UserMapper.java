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
        userDTO.setRole(user.getAuthorities().iterator().next().getAuthority());

        if(user.getBirthDate() != null) {
            userDTO.setBirthDate(user.getBirthDate());
        }

        if(user.getPhoneNumber() != null) {
            userDTO.setPhoneNumber(user.getPhoneNumber());
        }

        if(user.getTelegramUsername() != null) {
            userDTO.setTelegramUsername(user.getTelegramUsername());
        }

        if(user.getOrganization() != null) {
            userDTO.setOrganizationName(user.getOrganization().getName());
        }

        if(user.getAbout() != null) {
            userDTO.setAbout(user.getAbout());
        }

        if(user.getPhotoUrl() != null) {
            userDTO.setPhotoUrl(user.getPhotoUrl());
        }

        return userDTO;
    }
}

