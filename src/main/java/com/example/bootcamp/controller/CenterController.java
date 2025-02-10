package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.Volunteer_centers;
import com.example.bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/center")
@RequiredArgsConstructor
public class CenterController {
    private final UserService centerservice;

    @GetMapping
    public List<CenterDTO> getAllcenters() {
        return centerservice.getAllcenters();
    }


    @GetMapping("/{centers_id}")
    public ResponseEntity<CenterDTO> getcentersById(@PathVariable Long centers_id) {
        return ResponseEntity.ok(centerservice.getcentersById(centers_id));
    }

}

