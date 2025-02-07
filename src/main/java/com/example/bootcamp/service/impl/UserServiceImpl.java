package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.CreateUserDTO;
import com.example.bootcamp.dto.UpdateUserDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.Access;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exception.AccessNotFoundException;
import com.example.bootcamp.exception.UserAlreadyExistsException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.AccessRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.util.UserMapper;
import lombok.RequiredArgsConstructor;
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
    private final VolunteerCenterRepository volunteerCenterRepository;
    private final AccessRepository accessRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::convertToDto)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public UserDTO createUser(CreateUserDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        Optional<Access> accessOptional = accessRepository.findByAuthority("ROLE_USER");
        if (accessOptional.isEmpty()) {
            throw new AccessNotFoundException("Authority not found!");
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setContactInfo(dto.getContactInfo());
        user.setBiography(dto.getBiography());
        user.setPhoto(dto.getPhoto());
        user.setAuthorities(Set.of(accessOptional.get()));

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + user + " already exist!");
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setContactInfo(dto.getContactInfo());
        user.setBiography(dto.getBiography());
        user.setPhoto(dto.getPhoto());

        Optional<VolunteerCenter> optionalVolunteer_center = volunteerCenterRepository.findById(dto.getStatus());
        optionalVolunteer_center.ifPresent(user::setStatus);

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email " + username + " not found!");
        }

        return UserMapper.convertToDto(optionalUser.get());
    }
}
