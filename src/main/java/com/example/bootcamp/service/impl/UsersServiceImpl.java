package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.modal.Users;
import com.example.bootcamp.repository.UsersRepository;
import com.example.bootcamp.service.UsersService;
import com.example.bootcamp.util.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(UsersMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDTO getUserbyId(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.map(UsersMapper::convertDTO).orElse(null);
    }

    @Override
    public UsersDTO createUser(UsersDTO dto) {
        Users user = new Users();
        user.setCredentials(dto.getCredentials());
        user.setRole(dto.getRole());
        user.setProfile(dto.getProfile());
        Users savedUser = usersRepository.save(user);
        return UsersMapper.convertDTO(savedUser);
    }

    @Override
    public UsersDTO updatePerson(Long id, UsersDTO dto) {
        Optional<Users> existingUserOptional = usersRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            Users existingUser = existingUserOptional.get();
            existingUser.setCredentials(dto.getCredentials());
            existingUser.setRole(dto.getRole());
            existingUser.setProfile(dto.getProfile());
            Users updatedUser = usersRepository.save(existingUser);
            return UsersMapper.convertDTO(updatedUser);
        }
        return null;
    }

    @Override
    public void deletePerson(Long id) {
        usersRepository.deleteById(id);
    }
}
