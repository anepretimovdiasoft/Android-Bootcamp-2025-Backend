package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CentersDTO;
import com.example.bootcamp.service.CentersService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@AllArgsConstructor
public class CentersController {

    private final CentersService centersService;

    @GetMapping
    public List<CentersDTO> getAllCenters() {
        return centersService.getAllCenters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentersDTO> getCenterById(@PathVariable long id) {
        return ResponseEntity.ok(centersService.getCenterById(id));
    }

    @PostMapping
    public ResponseEntity<CentersDTO> createCenter(@RequestBody CentersDTO dto) {
        return ResponseEntity.ok(centersService.createCenter(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentersDTO> updateCenter(@PathVariable long id, @RequestBody CentersDTO dto) {
        return ResponseEntity.ok(centersService.updateCenter(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable long id) {
        centersService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}