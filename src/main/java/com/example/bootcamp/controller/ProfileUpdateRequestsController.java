package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ProfileUpdateRequestsDTO;
import com.example.bootcamp.service.ProfileUpdateRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile-update-requests")
@RequiredArgsConstructor
public class ProfileUpdateRequestsController {

    private final ProfileUpdateRequestsService profileUpdateRequestsService;

    @GetMapping
    public List<ProfileUpdateRequestsDTO> getAllProfileUpdateRequests() {
        return profileUpdateRequestsService.getAllProfileUpdateRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileUpdateRequestsDTO> getProfileUpdateRequestById(@PathVariable long id) {
        return ResponseEntity.ok(profileUpdateRequestsService.getProfileUpdateRequestById(id));
    }

    @PostMapping
    public ResponseEntity<ProfileUpdateRequestsDTO> createProfileUpdateRequest(@RequestBody ProfileUpdateRequestsDTO dto) {
        return ResponseEntity.ok(profileUpdateRequestsService.createProfileUpdateRequest(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileUpdateRequestsDTO> updateProfileUpdateRequest(@PathVariable long id, @RequestBody ProfileUpdateRequestsDTO dto) {
        return ResponseEntity.ok(profileUpdateRequestsService.updateProfileUpdateRequest(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileUpdateRequest(@PathVariable long id) {
        profileUpdateRequestsService.deleteProfileUpdateRequest(id);
        return ResponseEntity.noContent().build();
    }
}
