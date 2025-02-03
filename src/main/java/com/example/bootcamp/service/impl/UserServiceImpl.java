package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.VolunteerCentre;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.exception.VolunteerCentreNotFoundException;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.repository.VolunteerCentreRepository;
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
    private final VolunteerCentreRepository volunteerCentreRepository;

    @Override
    public List<UserDTO> getAllUser() {
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
    public UserDTO createUser(UserDTO dto) {
        Optional<VolunteerCentre> optionalVolunteer = volunteerCentreRepository.findByName(dto.getVolunteer());
        if (optionalVolunteer.isEmpty()) {
            throw new VolunteerCentreNotFoundException("Volunteer Centre not found");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPhotoUrl(dto.getPhotoUrl());
        user.setCoordinate_x(dto.getCoordinate_x());
        user.setCoordinate_y(dto.getCoordinate_y());
        user.setVolunteer(optionalVolunteer.get());


        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPhotoUrl(dto.getPhotoUrl());

        Optional<VolunteerCentre> optionalVolunteerCentre = volunteerCentreRepository.findByName(dto.getVolunteer());
        optionalVolunteerCentre.ifPresent(user::setVolunteer);

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
