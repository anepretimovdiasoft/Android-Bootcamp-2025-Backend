package com.example.bootcamp.service;

import com.example.bootcamp.dto.UsersDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    List<UsersDTO> getAllUsers();
    UsersDTO getUserbyId(Long id);
    UsersDTO createUser(UsersDTO dto);
    UsersDTO updatePerson(Long id, UsersDTO dto);
    void deletePerson(Long id);
}
