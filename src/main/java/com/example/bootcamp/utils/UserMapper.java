package com.example.bootcamp.utils;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSecondName(user.getSecondName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setInfo(user.getInfo());

        if(user.getOrganization() != null){
            userDTO.setOrganizationName(user.getOrganization().getName());
        }

        if(user.getContact() != null){
            userDTO.setPhoneNumber(user.getContact().getPhoneNumber());
            userDTO.setEmail(user.getContact().getEmail());
            userDTO.setTelegramLink(user.getContact().getTelegramLink());
        }

        return userDTO;
    }
}
