package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {
    private final CenterService centerService;

    @GetMapping
    public List<CenterDTO> getAllCenters() {
        return centerService.getAllCentres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getCenterById(@PathVariable Long id) {
        return ResponseEntity.ok(centerService.getCenterById(id));
    }

    @GetMapping("/{id}/volunteers")
    public List<UserDTO> getUsersInCenterByName(@RequestParam String name) {
        return centerService.getUsersByCenter(name);
    }

    // @GetMapping("/search")

    @GetMapping("/paginated")
    public ResponseEntity<Page<CenterDTO>> getAllUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(centerService.getAllCentresPaginated(pageable));
    }

}
