package com.example.bootcamp.controller;

import com.example.bootcamp.dto.AuthorityDTO;
import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.exception.RoleisNotAdmin;
import com.example.bootcamp.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/authority")
@RequiredArgsConstructor
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Authority add(@RequestBody Authority authority) {
        return authorityService.add(authority);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Authority> getAll() {
        return authorityService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@PathVariable Long id) {
        String idn = authorityService.isAdmin(id);
        if (Objects.equals(idn, "ROLE_ADMIN")) {
            return ResponseEntity.ok("is Admin");}
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("is Not Admin");
        }
    }
}
