package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CentersDTO;
import com.example.bootcamp.dto.FullCentersDTO;
import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.service.CentersService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<FullCentersDTO> getCenterById(@PathVariable long id) {
        FullCentersDTO fullCentersDTO = centersService.getFullCenterById(id);
        if (fullCentersDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(fullCentersDTO);
    }


    @PostMapping("/punish/{profileid}/{centerid}")
    public ResponseEntity<String> punishUserToCenter(
            @PathVariable long profileid,
            @PathVariable long centerid) {

        return ResponseEntity.ok(centersService.punishUserToCenter(profileid, centerid));
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

    @GetMapping("/paginated")
    public ResponseEntity<Page<CentersDTO>> getAllCentersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(centersService.getAllCentersPaginated(pageable));
    }
}