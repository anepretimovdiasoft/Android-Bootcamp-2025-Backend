package com.example.bootcamp.service;

import com.example.bootcamp.dto.ProfilesDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.dto.UsersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    List<UsersDTO> getAllUsers();
    List<ProfilesDTO> getAllUnoccupiedUsers();
    UsersDTO getUserbyId(Long id);

    UsersDTO createUser(UserRegisterDTO dto);

    UsersDTO updatePerson(Long id, UsersDTO dto);
    void deletePerson(Long id);

    UsersDTO getUserByUsername(String username);

    Page<ProfilesDTO> getAllUserPaginated(Pageable pageable);
}
