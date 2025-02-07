package com.example.bootcamp.service;

import com.example.bootcamp.dto.CreateUserDTO;
import com.example.bootcamp.dto.UpdateUserDTO;
import com.example.bootcamp.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(CreateUserDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);

    UserDTO getUserByUsername(String username);
}
