package com.example.bootcamp.util;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;

public class UserMapper {

    public static UserDTO convertToDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());

        return userDTO;
    }

    public static User convertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());

        return user;
    }
}
