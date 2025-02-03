package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/volunteer")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllPersons(){
        return userService.getAllUsers();
    };

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id){
        return ResponseEntity.ok(userService.getUserById(id));
    };

    @GetMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    };

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
