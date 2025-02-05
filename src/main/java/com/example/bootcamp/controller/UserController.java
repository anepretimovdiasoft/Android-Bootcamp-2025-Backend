package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import com.example.bootcamp.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    Page<UserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    UserDTO createUser(@RequestBody UserRegisterDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<String> getByEmail(@PathVariable String email) {
        UserDTO personDTO = UserMapper.convertToDTO(userRepository.findByEmail(email));
        return ResponseEntity.ok("User " + personDTO.getEmail() + " is registered");
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(Authentication authentication) {
        return ResponseEntity.ok(UserMapper.convertToDTO(
                userRepository.findByEmail(authentication.getName())
        ));
    }

}
