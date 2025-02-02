package com.example.bootcamp.controller;


import com.example.bootcamp.dto.entity.NotificationDTO;
import com.example.bootcamp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }

    @GetMapping("/sender/{id}")
    public ResponseEntity<List<NotificationDTO>> getAllBySenderId(@PathVariable long id) {
        return ResponseEntity.ok(notificationService.getAllBySenderId(id));
    }

    @GetMapping("/recipient/{id}")
    public ResponseEntity<List<NotificationDTO>> getAllByRecipientId(@PathVariable long id) {
        return ResponseEntity.ok(notificationService.getAllByRecipientId(id));
    }

    @GetMapping("/volunteer/{id}")
    public ResponseEntity<List<NotificationDTO>> getAllByVolunteerId(@PathVariable long id) {
        return ResponseEntity.ok(notificationService.getAllVolunteer(id));
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.ok(notificationService.create(notificationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable long id, @RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.ok(notificationService.update(id, notificationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
