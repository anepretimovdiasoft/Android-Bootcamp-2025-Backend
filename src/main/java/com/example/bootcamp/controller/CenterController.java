package com.example.bootcamp.controller;

import com.example.bootcamp.entity.Center;
import com.example.bootcamp.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @GetMapping
    public ResponseEntity<List<Center>> getCenters() {
        return ResponseEntity.ok(centerService.getAllCenters());
    }
}
