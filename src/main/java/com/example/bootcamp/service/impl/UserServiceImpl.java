package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.entity.Contact;
import com.example.bootcamp.entity.Organization;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.exception.UserAlreadyExistsException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.repository.OrganizationRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.utils.UserMapper;
import liquibase.pro.packaged.C;
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
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::convertToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UserNotFoundException("User with" + username + " not found!");
        }
        return UserMapper.convertToUserDTO(user.get());
    }

    @Override
    public UserDTO createUser(UserRegisterDTO userRegisterDTO) {
        if(userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("Username already exists!");
        }

        Optional<Organization> organization = organizationRepository.findByName(userRegisterDTO.getOrganizationName());

        Optional<Authority> authority = authorityRepository.findByAuthority("user");
        if(authority.isEmpty()) throw new RuntimeException("Authority not found!");

        User user = new User();

        organization.ifPresent(user::setOrganization);


        user.setName(userRegisterDTO.getName());
        user.setSecondName(userRegisterDTO.getSecondName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setAuthorities(Set.of(authority.get()));
        user.setInfo(userRegisterDTO.getInfo());

        Contact contact = new Contact();
        contact.setEmail(userRegisterDTO.getEmail());
        contact.setUser(user);
        contact.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        contact.setTelegramLink(userRegisterDTO.getTelegramLink());

        user.setContact(contact);

        return UserMapper.convertToUserDTO(userRepository.save(user));
    }


    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        Contact contact = user.getContact();

        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }

        user.setName(userDTO.getName());
        user.setSecondName(userDTO.getSecondName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setInfo(userDTO.getInfo());

        contact.setTelegramLink(userDTO.getTelegramLink());
        contact.setEmail(userDTO.getEmail());
        contact.setPhoneNumber(userDTO.getPhoneNumber());

        user.setContact(contact);

        Optional<Organization> organization = organizationRepository.findByName(userDTO.getOrganizationName());
        organization.ifPresent(user::setOrganization);

        return UserMapper.convertToUserDTO(userRepository.save(user));
    }

    @Override
    public Page<UserDTO> getAllUserPaginated(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserMapper::convertToUserDTO);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
