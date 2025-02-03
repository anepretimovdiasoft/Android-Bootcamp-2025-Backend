package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.exception.NoRequestParamsException;
import com.example.bootcamp.service.CenterService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/center")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @GetMapping
    public ResponseEntity<List<CenterDTO>> getAllCenters(
            @RequestParam(required = false) String lat,
            @RequestParam(required = false) String lng) {
        try {
            if (lat == null && lng == null) {
                return ResponseEntity.ok(centerService.getAllCenters(null, null));
            } else if (lat != null && lng != null) {
                double latNum = Double.parseDouble(lat);
                double lngNum = Double.parseDouble(lng);
                return ResponseEntity.ok(centerService.getAllCenters(latNum, lngNum));
            } else {
                throw new NoRequestParamsException("Invalid number format!(lat, lng)");
            }
        } catch (NumberFormatException e) {
            throw new NoRequestParamsException("Invalid number format!(lat, lng)");
        }
    }

    @GetMapping("/closest")
    public ResponseEntity<List<CenterDTO>> getClosestCenters(
            @RequestParam(required = false) String lat,
            @RequestParam(required = false) String lng) {
        try {
            if (lat == null  || lng == null) {
                throw new NoRequestParamsException("No request params!");
            }
            double latNum = Double.parseDouble(lat);
            double lngNum = Double.parseDouble(lng);
            return ResponseEntity.ok(centerService.getClosestCenters(latNum, lngNum));
        } catch (NumberFormatException e) {
            throw new NoRequestParamsException("Invalid number format!(lat, lng)");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getCenterById(
            @PathVariable Long id,
            @RequestParam(required = false) String lat,
            @RequestParam(required = false) String lng) {
        try {
            if (lat == null && lng == null) {
                return ResponseEntity.ok(centerService.getCenterById(id, null, null));
            } else if (lat != null && lng != null) {
                double latNum = Double.parseDouble(lat);
                double lngNum = Double.parseDouble(lng);
                return ResponseEntity.ok(centerService.getCenterById(id, latNum, lngNum));
            } else {
                throw new NoRequestParamsException("Invalid number format!(lat, lng)");
            }
        } catch (NumberFormatException e) {
            throw new NoRequestParamsException("Invalid number format!(lat, lng)");
        }
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserDTO>> getCenterUsers(@PathVariable Long id) {
            return ResponseEntity.ok(centerService.getCenterUsers(
                    centerService.getCenterById(id, null, null)));
    }
}
