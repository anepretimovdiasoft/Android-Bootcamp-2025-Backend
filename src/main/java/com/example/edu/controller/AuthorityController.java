package com.example.edu.controller;

import com.example.edu.dto.authorities.AuthorityCreateDTO;
import com.example.edu.dto.authorities.AuthorityDTO;
import com.example.edu.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorityDTO add(@RequestBody AuthorityCreateDTO dto) {
        return authorityService.add(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorityDTO> getAll() {
        return authorityService.getAll();
    }

}
