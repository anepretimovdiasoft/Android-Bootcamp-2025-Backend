package com.example.bootcamp.service;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(UserRegisterDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    boolean getUserStatus(Long id);

    boolean updateUserStatus(Long id, boolean status);

    void deleteUser(Long id);

    UserDTO getUserByUsername(String username);

    Page<UserDTO> getAllUsersPaginated(Pageable pageable);
}
