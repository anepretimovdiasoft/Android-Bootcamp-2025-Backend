package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.Organization;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.exception.OrganizationNotFoundException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.repository.OrganizationRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::convertToDTO);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public UserDTO createUser(UserRegisterDTO dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAuthorities(Set.of(
                authorityRepository.findByAuthority("USER").get()
        ));

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
