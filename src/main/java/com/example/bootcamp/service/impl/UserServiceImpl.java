package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.entity.Center;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.exception.*;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.util.Mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.h2.jdbc.JdbcSQLDataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        return userRepository.findAll().stream().map(UserMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getFreeUsers() {
        return userRepository.findByCenterId(null).stream().map(UserMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getLatestEnrollments() {
        List<User> users = userRepository.findAll(
                PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "joinedAt"))
        ).getContent();
        return users.stream().filter(user -> user.getJoinedAt() != null).map(UserMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::convertToDTO).
                orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
    }

    @Override
    public UserDTO createUser(UserRegisterDTO registerDTO)  {
        try {
            if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
                throw new UsernameAlreadyExistsException("Username" + registerDTO.getUsername() + " already exists!");
            }
            else if (userRepository.existsByEmail(registerDTO.getEmail())) {
                throw new EmailAlreadyExistsException("Email " + registerDTO.getEmail() + " already exists!");
            }

            Optional<Authority> roleUser = authorityRepository.findByAuthority("ROLE_USER");
            if (roleUser.isEmpty()) {
                throw new AuthorityNotFoundException("Authority ROLE_USER not found!");
            }

            User user = UserMapper.convertFromRegisterDTO(registerDTO);
            user.setAuthorities(Set.of(roleUser.get()));
            user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            return UserMapper.convertToDTO(userRepository.save(user));
        }
        catch (DataIntegrityViolationException e) {
            Throwable rootCause = e;
            while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
                rootCause = rootCause.getCause();
            }

            if (rootCause instanceof JdbcSQLDataException) {
                JdbcSQLDataException constraintEx = (JdbcSQLDataException) rootCause;

                String sqlMessage = constraintEx.getMessage();

                if (sqlMessage.contains("PASSWORD VARCHAR(100)")) {
                    throw new InvalidPasswordException("Password " + registerDTO.getPassword() + " too large(max 100 symbols)!");
                } else if (sqlMessage.contains("NAME")) {
                    throw new InvalidNameException("Name " + registerDTO.getName() + " too large(max 100 symbols)!");
                } else {
                    throw new OtherException("Неизвестная ошибка!");
                }
            } else {
                throw new OtherException("Неизвестная ошибка!");
            }
        }
    }

    @Override
    public UserDTO getByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with name " + username + " not found!");
        }
        return UserMapper.convertToDTO(user.get());
    }

    @Override
    public UserDTO updateUserProfile(Long id, UserDTO userDTO) {
        try {
            User old_user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User " + id + " not found!"));
            User user = UserMapper.convertFromDTO(userDTO);
            user.setId(id);

            user.setCreatedAt(old_user.getCreatedAt());
            user.setPassword(old_user.getPassword());
            user.setAuthorities(old_user.getAuthorities());
            user.setCenter(old_user.getCenter());
            user.setJoinedAt(old_user.getJoinedAt());
            return UserMapper.convertToDTO(userRepository.save(user));
        }
        catch (DataIntegrityViolationException e) {
            Throwable rootCause = e;
            while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
                rootCause = rootCause.getCause();
            }

            if (rootCause instanceof JdbcSQLDataException) {
                JdbcSQLDataException constraintEx = (JdbcSQLDataException) rootCause;

                String sqlMessage = constraintEx.getMessage();

                if (sqlMessage.contains("USER(EMAIL)")) {
                    throw new EmailAlreadyExistsException("Email " + userDTO.getEmail() + " already exists!");
                } else if (sqlMessage.contains("BIRTH_DATE")) {
                    throw new InvalidBirthDateException("Birth Date " + userDTO.getBirthDate() + " too large(max 10 symbols)!");
                } else if (sqlMessage.contains("NAME")) {
                    throw new InvalidNameException("Name " + userDTO.getName() + " too large(max 100 symbols)!");
                } else if (sqlMessage.contains("DESCRIPTION")) {
                    throw new InvalidDescriptionException("Description " + userDTO.getDescription() + " too large(max 200 symbols)!");
                } else {
                    throw new OtherException("Неизвестная ошибка!");
                }
            } else {
                throw new OtherException("Неизвестная ошибка!");
            }
        }
    }

    @Override
    public UserDTO editCenter(Long id, String center) {

        User old_user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
        Optional<Center> optionalCenter = centerRepository.findByName(center);
        if (optionalCenter.isPresent()) {
            old_user.setCenter(optionalCenter.get());
            old_user.setJoinedAt(Timestamp.valueOf(LocalDateTime.now()));
        } else if ("kick".equals(center)) {
            if (old_user.getCenter() != null) {
                old_user.setCenter(null);
                old_user.setJoinedAt(null);
            } else {
                throw new UserHasNoCenterException("User with id " + id + " has no center!");
            }
        } else {
            throw new CenterNotFoundException("Center with name " + center + " not found!");
        }

        return UserMapper.convertToDTO(userRepository.save(old_user));
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id " + id + " not found!");
        }
    }
}
