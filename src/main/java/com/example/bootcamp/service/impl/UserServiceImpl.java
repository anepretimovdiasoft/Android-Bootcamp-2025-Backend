package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.Organization;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.exception.OrganizationNotFoundException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.OrganizationRepository;
import com.example.bootcamp.repository.RoleRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        Optional<Organization> organization = organizationRepository.findByName(dto.getOrganizationName());
        if(organization.isEmpty()) {
            throw new OrganizationNotFoundException("Organization with name " + dto.getOrganizationName() + " not found");
        }
        User user = new User();

        user.setPassword("HelloWorld");
        user.setRole(roleRepository.getById(2L));

        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthDate(dto.getBirthDate());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setTelegramUsername(dto.getTelegramUsername());
        user.setOrganization(organization.get());
        user.setAbout(dto.getAbout());
        user.setPhotoUrl(dto.getPhotoUrl());

        return UserMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthDate(dto.getBirthDate());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setTelegramUsername(dto.getTelegramUsername());
        user.setAbout(dto.getAbout());
        user.setPhotoUrl(dto.getPhotoUrl());

        Optional<Organization> organization = organizationRepository.findByName(dto.getOrganizationName());
        organization.ifPresent(user::setOrganization);

        return UserMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
