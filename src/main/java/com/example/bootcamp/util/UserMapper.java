package com.example.bootcamp.util;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public UserDTO convertToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setPhotoUrl(user.getPhotoUrl());
        userDTO.setCoordinate_x(user.getCoordinate_x());
        userDTO.setCoordinate_y(user.getCoordinate_y());
        userDTO.setVolunteer(user.getVolunteer().getName());
        return userDTO;
    }
}
