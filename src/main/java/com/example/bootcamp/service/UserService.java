package com.example.bootcamp.service;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    List<UserDTO> getFreeUsers();

    List<UserDTO> getLatestEnrollments();

    UserDTO getUserById(Long id);

    User createUser(User user);

    UserDTO updateUserProfile(Long id, UserDTO userDTO);

    UserDTO editCenter(Long id, String center);

    void deleteUser(Long id);
}
