package com.example.bootcamp.service;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.Volunteer_centers;
import com.example.bootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(UserRegisterDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);
    void detachVolunteerFromCenter(Long volunteer_id);
    void attachVolunteerToCenter(Long volunteer_id, Long center_id);

    UserDTO getUserByUsername(String username);

    List<UserDTO> getActiveUsers();
    List<UserDTO> getInactiveUsers();



    CenterDTO getcentersById(Long centers_id);
    List<CenterDTO> getAllcenters();



    List<UserDTO> findVolunteersByCenter(Long center_id);

}
