package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUserPaginated(pageable));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/user/{/id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/user/{/id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(Authentication authentication){
        return ResponseEntity.ok(userService.getUserByUsername(authentication.getName()));
    }

    @GetMapping("/user/username/{login}")
    public ResponseEntity<String> getUserByLogin(@PathVariable String login){
        UserDTO userDTO = userService.getUserByUsername(login);
        return ResponseEntity.ok("User " + userDTO.getUsername() + " is registered");
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> createPerson(@RequestBody UserRegisterDTO userRegisterDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRegisterDTO));
    }
}
