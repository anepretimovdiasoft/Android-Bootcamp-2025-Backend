package com.example.bootcamp.controller;
import com.example.bootcamp.dto.ProfilesDTO;
import com.example.bootcamp.service.ProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfilesController {
    private final ProfilesService profilesService;
    @GetMapping
    public List<ProfilesDTO> getAllProfiles() {
        return profilesService.getAllProfiles();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfilesDTO> getProfileById(@PathVariable long id) {
        return ResponseEntity.ok(profilesService.getProfileById(id));
    }
}