package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.entity.Roles;
import com.example.bootcamp.entity.Users;
import com.example.bootcamp.exception.PersonAlreadyExistsException;
import com.example.bootcamp.exception.PersonNotFoundException;
import com.example.bootcamp.repository.RolesRepository;
import com.example.bootcamp.repository.UsersRepository;
import com.example.bootcamp.service.UsersService;
import com.example.bootcamp.util.UsersMapper;
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
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

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
    public UsersDTO createUser(UserRegisterDTO dto) {

        if(usersRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new PersonAlreadyExistsException("Username already exists");
        }

        Optional<Roles> roleUser = rolesRepository.findByRole("ROLE_USER");

        if (roleUser.isEmpty()) throw new RuntimeException("Roles not found");

        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(roleUser.get()));


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
        } else {
        throw new PersonNotFoundException("Person not found");}
    }

    @Override
    public void deletePerson(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public UsersDTO getUserByUsername(String username) {
        Optional<Users> optionalUsers = usersRepository.findByUsername(username);

        if (optionalUsers.isEmpty()) {
            throw new PersonNotFoundException("Usr with username" + username + "not found");
        }
        return UsersMapper.convertDTO(optionalUsers.get());
    }

    @Override
    public Page<UsersDTO> getAllUserPaginated(Pageable pageable) {
        return usersRepository.findAll(pageable)
                .map(UsersMapper::convertDTO);
    }
}
