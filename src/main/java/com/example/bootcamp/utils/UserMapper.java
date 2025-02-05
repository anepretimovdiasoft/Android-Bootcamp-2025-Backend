package com.example.bootcamp.utils;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public UserDTO convertToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSecondName(user.getSecondName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
}
