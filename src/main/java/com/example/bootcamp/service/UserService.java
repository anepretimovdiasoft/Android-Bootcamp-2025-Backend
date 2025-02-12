package com.example.bootcamp.service;

import com.example.bootcamp.dto.CreateUserDTO;
import com.example.bootcamp.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(CreateUserDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);

    UserDTO getUserByUsername(String username);

    Page<UserDTO> getAllUserPaginated(Pageable pageable);

//    UserDTO getUserByStatus(Long status);
}
