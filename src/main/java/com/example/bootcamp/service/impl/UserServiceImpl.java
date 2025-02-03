package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.Volunteer_center;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.exception.Volunteer_centerNotFoundException;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.repository.Volunteer_centerRepository;
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
    private final Volunteer_centerRepository volunteerCenterRepository;

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
    public UserDTO createUser(UserDTO dto) {
        Optional<Volunteer_center> optionalVolunteer_center = volunteerCenterRepository.findByVolunteer_centerId(dto.getId());
        if (optionalVolunteer_center.isEmpty()) {
            throw new Volunteer_centerNotFoundException("Volunteer center not found!");
        }

        User user = new User();
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setEmail(dto.getEmail());
        user.setContact_info(dto.getContact_info());
        user.setBiography(dto.getBiography());
        user.setPhoto(dto.getPhoto());
        user.setStatus(optionalVolunteer_center.get());

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setEmail(dto.getEmail());
        user.setContact_info(dto.getContact_info());
        user.setBiography(dto.getBiography());
        user.setPhoto(dto.getPhoto());

        Optional<Volunteer_center> optionalVolunteer_center = volunteerCenterRepository.findByVolunteer_centerId(dto.getId());
        optionalVolunteer_center.ifPresent(user::setStatus);

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
