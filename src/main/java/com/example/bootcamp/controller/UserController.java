package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.exception.NoRequestBodyException;
import com.example.bootcamp.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/free")
    public ResponseEntity<List<UserDTO>> getFreeUsers() {
        return ResponseEntity.ok(userService.getFreeUsers());
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<UserDTO>> getLatestEnrollments() {
        return ResponseEntity.ok(userService.getLatestEnrollments());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody(required = false) UserRegisterDTO user) {
        if (user == null) {
            throw new NoRequestBodyException("No or wrong request body!");
        }
            UserDTO createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getByUsername(username);
        if (username == null) {
            throw new NoRequestBodyException("No or wrong request body!");
        }
        return ResponseEntity.ok(userDTO.getUsername());
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(Authentication authentication) {
        if (authentication == null) {
            throw new NoRequestBodyException("No or wrong request body!");
        }
        return ResponseEntity.ok(userService.getByUsername(authentication.getName()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserProfile(
            @PathVariable Long id,
            @RequestBody(required = false) UserDTO userDTO) {

        if (userDTO == null) {
            throw new NoRequestBodyException("No or wrong request body!");
        }
        return ResponseEntity.ok(userService.updateUserProfile(id, userDTO));
    }

    @PutMapping("/join/{id}")
    public ResponseEntity<UserDTO> editCenter(
            @RequestBody(required = false) Map<String, String> request,
            @PathVariable Long id) {

        String center = request.get("center");
        if (center == null) {
            throw new NoRequestBodyException("No or wrong request body!");
        }
        return ResponseEntity.ok(userService.editCenter(id, center));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(required = false) Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
