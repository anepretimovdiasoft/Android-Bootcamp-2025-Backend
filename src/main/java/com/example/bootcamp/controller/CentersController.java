package com.example.bootcamp.controller;

import com.example.bootcamp.dto.entity.CenterDTO;
import com.example.bootcamp.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/centers")
public class CentersController {
    private final CenterService centerService;

    @GetMapping
    public ResponseEntity<List<CenterDTO>> getAllCenters() {
        return ResponseEntity.ok(centerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getCenterById(@PathVariable long id) {
        return ResponseEntity.ok(centerService.getById(id));
    }

    @GetMapping("/sorted/distance")
    public ResponseEntity<List<CenterDTO>> getSortedCenters(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude
    ) {
        return ResponseEntity.ok(centerService.getAllSorted(latitude, longitude));
    }

    @PostMapping
    public ResponseEntity<CenterDTO> createCenter(@RequestBody CenterDTO centerDTO) {
        return ResponseEntity.ok(centerService.create(centerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CenterDTO> updateCenter(@PathVariable long id, @RequestBody CenterDTO centerDTO) {
        return ResponseEntity.ok(centerService.update(id, centerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable long id) {
        centerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
