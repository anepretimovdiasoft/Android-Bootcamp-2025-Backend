package com.example.bootcamp.service;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);

    UserDTO createUser(UserRegisterDTO userRegisterDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    Page<UserDTO> getAllUserPaginated(Pageable pageable);

    void deleteUser(Long id);
}
