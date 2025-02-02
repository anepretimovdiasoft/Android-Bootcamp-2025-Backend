package com.example.bootcamp.util.Mapper;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setDescription(user.getDescription());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setAdminRights(user.isAdminRights());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setJoinedAt(user.getJoinedAt());
        if (user.getCenter() != null) {
            userDTO.setCenter(user.getCenter().getName());
        }
        return userDTO;
    }
    public User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setDescription(userDTO.getDescription());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setAdminRights(userDTO.isAdminRights());
        user.setBirthDate(userDTO.getBirthDate());
        user.setJoinedAt(userDTO.getJoinedAt());
        return user;
    }
}
