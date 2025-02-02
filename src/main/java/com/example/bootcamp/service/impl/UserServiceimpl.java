package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.Volunteer_centers;
import com.example.bootcamp.exception.CentreNotFoundException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.RolesRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.UnmodifiableSetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final CenterRepository centerRepository;

    @Override
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id){

        return userRepository.findById(id)
                .map(UserMapper::convertToDto)
                .orElseThrow(() -> new UserNotFoundException("User not found!")) ;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        Optional<Volunteer_centers> optionalVolunteerCenters = centerRepository.findByTitle(dto.getCenters());

        if (optionalVolunteerCenters.isEmpty()){
            throw new CentreNotFoundException("Centre not found !");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setMail(dto.getMail());
        user.setPhone(dto.getPhone());
        user.setCenters(optionalVolunteerCenters.get());

        return UserMapper.convertToDto(userRepository.save(user));

    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.setName(dto.getName());
        user.setMail(dto.getMail());
        user.setUsername(dto.getUsername());
        user.setPhone(dto.getPhone());

        Optional<Volunteer_centers> optionalVolunteerCenters = centerRepository.findByTitle(dto.getCenters());
        optionalVolunteerCenters.ifPresent(user::setCenters);

        return UserMapper.convertToDto(userRepository.save(user));

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUser(Long id){
        System.out.println("Delete: " + id);
        userRepository.deleteUserById(id);
    }

}
