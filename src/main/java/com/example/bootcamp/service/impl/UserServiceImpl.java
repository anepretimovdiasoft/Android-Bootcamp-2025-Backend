package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exception.CenterNotFoundException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.util.UserConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CenterRepository centerRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserConvert::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserConvert::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("Volunteer not found!"));
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        Optional<VolunteerCenter> center = centerRepository.findByName(dto.getCenterName());
        if (center.isEmpty()) {
            throw new CenterNotFoundException("Department not found");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhone_number());
        user.setEmail(dto.getEmail());
        user.setAbout(dto.getAbout());
        user.setPassword(dto.getPassword());
        user.setStatusWork(dto.isStatusWork());

        return UserConvert.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Volunteer not found!"));

        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhone_number());
        user.setEmail(dto.getEmail());
        user.setAbout(dto.getAbout());
        user.setPassword(dto.getPassword());
        user.setStatusWork(dto.isStatusWork());

        Optional<VolunteerCenter> center = centerRepository.findByName(dto.getCenterName());
        center.ifPresent(user::setVolunteerCenter);

        return UserConvert.convertToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}