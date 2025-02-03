package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.OrganizationRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

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
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserDTO createUser(UserDTO dto) {

        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        user.setLastName(dto.getLastName());
        user.setInfo(dto.getInfo());

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        user.setLastName(dto.getLastName());
        user.setInfo(dto.getInfo());

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
