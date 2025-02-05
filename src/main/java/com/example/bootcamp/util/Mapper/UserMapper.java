package com.example.bootcamp.util.Mapper;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.User;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setDescription(user.getDescription());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setJoinedAt(user.getJoinedAt());
        userDTO.setAuthorities(user.getAuthorities().iterator().next().getAuthority());
        if (user.getCenter() != null) {
            userDTO.setCenter(user.getCenter().getName());
            userDTO.setCenterDescription(user.getCenter().getDescription());
        }
        return userDTO;
    }
    public User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setDescription(userDTO.getDescription());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setBirthDate(userDTO.getBirthDate());
        user.setJoinedAt(userDTO.getJoinedAt());
        return user;
    }
    public User convertFromRegisterDTO(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setName(userRegisterDTO.getName());
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        return user;
    }

    public UserRegisterDTO convertToRegisterDTO(User user) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setName(user.getName());
        userRegisterDTO.setUsername(user.getUsername());
        userRegisterDTO.setEmail(user.getEmail());
        return userRegisterDTO;
    }
}
