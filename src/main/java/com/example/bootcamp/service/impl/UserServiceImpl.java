package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.exception.VolunteerCenterNotFoundException;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VolunteerCenterRepository volunteerCenterRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::convertToDto)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        VolunteerCenter volunteerCenter = volunteerCenterRepository.findById(userDTO.getVolunteerCenterId())
                .orElseThrow(() -> new VolunteerCenterNotFoundException("Volunteer Center not found!"));

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setVolunteerCenter(volunteerCenter);

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());

        if (userDTO.getVolunteerCenterId() != null) {
            VolunteerCenter volunteerCenter = volunteerCenterRepository.findById(userDTO.getVolunteerCenterId())
                    .orElseThrow(() -> new VolunteerCenterNotFoundException("Volunteer Center not found!"));
            user.setVolunteerCenter(volunteerCenter);
        }

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
