package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.ProfilesDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.entity.*;
import com.example.bootcamp.exception.PersonAlreadyExistsException;
import com.example.bootcamp.exception.PersonNotFoundException;
import com.example.bootcamp.repository.*;
import com.example.bootcamp.service.UsersService;
import com.example.bootcamp.util.ProfilesMapper;
import com.example.bootcamp.util.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final CredentialsRepository credentialsRepository;
    private final ProfileRepository profileRepository;
    public final CenterRepository centerRepository;
    private final AuthorityRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsersDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(UsersMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfilesDTO> getAllUnoccupiedUsers() {
        return profileRepository.findAll().stream()
                .filter(profile -> profile.getCenter() == null)  // Фильтрация пользователей без центра
                .map(ProfilesMapper::convertDTO)
                .collect(Collectors.toList());
    }



    @Override
    public UsersDTO getUserbyId(Long id) {
        return usersRepository.findById(id)
                .map(UsersMapper::convertDTO)
                .orElseThrow(() -> new PersonNotFoundException("Person not found!"));
    }

    @Override
    public UsersDTO createUser(UserRegisterDTO dto) {
        if(usersRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new PersonAlreadyExistsException("Username already exists");
        }

        Optional<Authority> roleUser = rolesRepository.findByAuthority("ROLE_USER");

        if (roleUser.isEmpty()) throw new RuntimeException("Roles not found");
        Profile profile = new Profile();
        Credentials credentials = new Credentials();
        credentials.setLogin(dto.getUsername());
        credentials.setName(dto.getName());
        credentials.setLastname(dto.getLastname());
        credentials.setHashedPassword(passwordEncoder.encode(dto.getPassword()));
        credentials = credentialsRepository.save(credentials);
        profile.setName(credentials.getName());
        profile.setLastname(credentials.getLastname());
        profile = profileRepository.save(profile);

        Users user = new Users();
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAuthorities(Set.of(roleUser.get()));
        user.setCredentials(credentials);
        user.setProfile(profile);
        user.setAuthority(roleUser.get());
        user.setCreated(localDateTime);
        user.setUpdated(localDateTime);




        Users savedUser = usersRepository.save(user);
        return UsersMapper.convertDTO(savedUser);
    }

    @Override
    public UsersDTO updatePerson(Long id, UsersDTO dto) {
        Optional<Users> existingUserOptional = usersRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            Users existingUser = existingUserOptional.get();
//            existingUser.setProfile(dto.getProfileId());
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
            throw new PersonNotFoundException("User with username " + username + "not found");
        }
        return UsersMapper.convertDTO(optionalUsers.get());
    }

    @Override
    public Page<ProfilesDTO> getAllUserPaginated(Pageable pageable) {
        return profileRepository.findAll(pageable)
                .map(ProfilesMapper::convertDTO);
    }
}
