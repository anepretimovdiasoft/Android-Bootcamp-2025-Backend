package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private UserDTO dto;

    @GetMapping
    public List<UserDTO> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getByUsername(@PathVariable String username){
        UserDTO userDTO = userService.getUserByUsername(username);
        return ResponseEntity.ok("User" + userDTO.getUsername() + "is registered");

    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id){
        return ResponseEntity.ok(userService.getUserById(id));

    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(Authentication authentication){
        return ResponseEntity.ok(userService.getUserByUsername(authentication.getName()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRegisterDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
