package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public List<UsersDTO> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(usersService.getUserbyId(id));
    }

    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO dto) {
        return ResponseEntity.ok(usersService.createUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable long id, @RequestBody UsersDTO dto) {
        return ResponseEntity.ok(usersService.updatePerson(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        usersService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
