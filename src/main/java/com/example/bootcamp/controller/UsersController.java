package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UserRegisterDTO dto) {
        return ResponseEntity.ok(usersService.createUser(dto));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username) {
        UsersDTO usersDTO = usersService.getUserByUsername(username);
        return ResponseEntity.ok("User " + usersDTO.getUsername() + " is registered");
    }
    @GetMapping("/login")
    public ResponseEntity<UsersDTO> login(Authentication authentication) {
        return ResponseEntity.ok(usersService.getUserByUsername(authentication.getName()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable long id, @RequestBody UsersDTO dto) {
        return ResponseEntity.ok(usersService.updatePerson(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        usersService.deletePerson(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<UsersDTO>> getAllUserPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(usersService.getAllUserPaginated(pageable));
    }
}
