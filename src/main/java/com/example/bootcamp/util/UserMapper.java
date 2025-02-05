package com.example.bootcamp.util;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public UserDTO convertToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setMail(user.getMail());
        userDTO.setPhone(user.getPhone());
        userDTO.setCenters(user.getCenters().getTitle());


        return userDTO;
    }
}
