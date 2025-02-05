package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDto;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.VolunteerCenter;
import com.example.bootcamp.exception.CenterNotFoundException;
import com.example.bootcamp.exception.UserAlreadyExistsException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.util.ConverterDTO;
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
    private final CenterRepository centerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(ConverterDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(ConverterDTO::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("Volunteer not found!"));
    }

    @Override
    public UserDTO createUser(UserRegisterDto dto) {
        VolunteerCenter center = centerRepository.findByName(dto.getCenterName())
                .orElseThrow(() -> new CenterNotFoundException("Department not found"));

        Optional<Authority> roleUser = authorityRepository.findByAuthority("ROLE_USER");
        if (roleUser.isEmpty()) throw new RuntimeException("Authority not found!");

        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAuthorities(Set.of(roleUser.get()));
        user.setVolunteerCenter(center);

        return ConverterDTO.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Volunteer not found!"));

        if (userRepository.findByUsername(dto.getUsername()).isPresent())
            throw new UserAlreadyExistsException("Username already exists");

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPhoneNumber(dto.getPhone_number());
        user.setEmail(dto.getEmail());
        user.setAbout(dto.getAbout());
        user.setPassword(dto.getPassword());
        user.setStatusWork(dto.isStatusWork());

        Optional<VolunteerCenter> center = centerRepository.findByName(dto.getCenterName());
        center.ifPresent(user::setVolunteerCenter);

        return ConverterDTO.convertToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean getUserStatus(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Volunteer not found!"));
        return user.isStatusWork();
    }

    @Override
    public boolean updateUserStatus(Long id, boolean status) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Volunteer not found!"));
        user.setStatusWork(status);
        userRepository.save(user);

        return user.isStatusWork();
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) throw new UserNotFoundException("User with username " + username + " not found");

        return ConverterDTO.convertToDTO(userOptional.get());
    }
}