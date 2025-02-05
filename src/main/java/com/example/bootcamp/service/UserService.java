package com.example.bootcamp.service;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserDTO> getAllUsers(Pageable pageable);

    UserDTO getUserById(Long id);

    UserDTO createUser(UserRegisterDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);
}
