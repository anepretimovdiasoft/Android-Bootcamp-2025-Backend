package com.example.bootcamp.service;

import com.example.bootcamp.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);
}
