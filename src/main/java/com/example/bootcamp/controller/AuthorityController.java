package com.example.bootcamp.controller;

import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authority")
@RequiredArgsConstructor
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<Authority>> getAllAuthority(){
        return ResponseEntity.status(HttpStatus.OK).body(authorityService.getAllAuthority());
    }

    @PostMapping
    public ResponseEntity<Authority> addAuthority(@RequestBody Authority authority){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorityService.add(authority));

    }
}
