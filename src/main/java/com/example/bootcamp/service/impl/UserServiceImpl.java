package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.Roles;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.Volunteer_centers;
import com.example.bootcamp.exception.CentreNotFoundException;
import com.example.bootcamp.exception.UserAlreadyExistsException;
import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.RolesRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.util.CenterMapper;
import com.example.bootcamp.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.UnmodifiableSetException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.ClassUtils.isPresent;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final CenterRepository centersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) throw new UserNotFoundException("User with username " + username + " not found");
        return UserMapper.convertToDto(userOptional.get());
    }

    @Override
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getActiveUsers() {

        return userRepository.findByActive(true).stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());

    }
    public List<UserDTO> getInactiveUsers() {

        return userRepository.findByActive(false).stream()
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
    public UserDTO createUser(UserRegisterDTO dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent())
            throw new UserAlreadyExistsException("Username already exists");

        Optional<Volunteer_centers> optionalVolunteercenters = Optional.empty();
        if (dto.getCenters() != null && !dto.getCenters().isEmpty()) {
            optionalVolunteercenters = centersRepository.findByTitle(dto.getCenters());

            if (optionalVolunteercenters.isEmpty()) {
                throw new CentreNotFoundException("Centre not found !");
            }
        }

        Optional<Roles> roleUser = rolesRepository.findByRole("ROLE_USER");
        if (roleUser.isEmpty()) throw new RuntimeException("Role not found!");



        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setMail(dto.getMail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        if (!user.getActive()) {
            user.setCenters(null);
        }

        else{
        user.setCenters(optionalVolunteercenters.get());
        }



        if (optionalVolunteercenters.isPresent()) {
            user.setCenters(optionalVolunteercenters.get());
        } else {
            user.setCenters(null);
        }

        user.setRoles(Set.of(roleUser.get()));
        return UserMapper.convertToDto(userRepository.save(user));
    }


    @Override
    public UserDTO updateUser(Long id, UserDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if(userRepository.findByUsername(dto.getUsername()).isPresent())
            throw new UserAlreadyExistsException("Username already exists!");

        user.setName(dto.getName());
        user.setMail(dto.getMail());
        user.setUsername(dto.getUsername());
        user.setPhoto_url(dto.getPhoto_url());

        Optional<Volunteer_centers> optionalVolunteercenters = centersRepository.findByTitle(dto.getCenters());
        
        optionalVolunteercenters.ifPresent(user::setCenters);

        return UserMapper.convertToDto(userRepository.save(user));

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUser(Long id){
        System.out.println("Delete: " + id);
        userRepository.deleteUserRoleByUserId(id);

        userRepository.deleteUserById(id);
    }

    @Override
    public List<CenterDTO> getAllcenters() {
        List<Volunteer_centers> centers = centersRepository.findAll();
        {
            return centers.stream()
                    .map(Centers -> CenterMapper.convertToDto(Centers))
                    .collect(Collectors.toList());
        }
    }

   @Override
    public CenterDTO getcentersById(Long id) {
       return centersRepository.findById(id)
               .map(CenterMapper::convertToDto)
               .orElseThrow(() -> new CentreNotFoundException("center not found!")) ;

   }


    @Override
    public List<UserDTO> findVolunteersByCenter(Long center_id) {
        List<User> activeVolunteers = userRepository.findByActiveTrueAndCenters_id(center_id);
        return activeVolunteers.stream()
                .map(users -> UserMapper.convertToDto(users))
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public void attachVolunteerToCenter(Long volunteer_id, Long center_id) {
        User volunteer = userRepository.findById(volunteer_id)
                .orElseThrow(() -> new UserNotFoundException("Volunteer not found"));

        Volunteer_centers center = centersRepository.findById(center_id)
                .orElseThrow(() -> new CentreNotFoundException("Center not found"));

        volunteer.setCenters(center);
        userRepository.save(volunteer);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public void detachVolunteerFromCenter(Long volunteer_id) {
        User volunteer = userRepository.findById(volunteer_id)
                .orElseThrow(() -> new UserNotFoundException("Volunteer not found"));

        volunteer.setCenters(null);
        userRepository.save(volunteer);
    }
}
