package com.example.bootcamp.controller;

import com.example.bootcamp.dto.StatusesDTO;
import com.example.bootcamp.service.StatusesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
@RequiredArgsConstructor
public class StatusesController {

    private final StatusesService statusesService;

    @GetMapping
    public List<StatusesDTO> getAllStatuses() {
        return statusesService.getAllStatuses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusesDTO> getStatusById(@PathVariable long id) {
        return ResponseEntity.ok(statusesService.getStatusById(id));
    }

    @PostMapping
    public ResponseEntity<StatusesDTO> createStatus(@RequestBody StatusesDTO dto) {
        return ResponseEntity.ok(statusesService.createStatus(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusesDTO> updateStatus(@PathVariable long id, @RequestBody StatusesDTO dto) {
        return ResponseEntity.ok(statusesService.updateStatus(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable long id) {
        statusesService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}
