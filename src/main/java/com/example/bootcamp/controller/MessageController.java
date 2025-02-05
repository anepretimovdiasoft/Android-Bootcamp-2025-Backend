package com.example.bootcamp.controller;

import com.example.bootcamp.entity.Message;
import com.example.bootcamp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestParam Long userId, @RequestParam String content,
                               @RequestParam Boolean isAdminMessage, @RequestParam Boolean isNotification) {
        return messageService.sendMessage(userId, content, isAdminMessage, isNotification);
    }

    @GetMapping("/user/{userId}")
    public List<Message> getMessagesByUserId(@PathVariable Long userId) {
        return messageService.getMessagesByUserId(userId);
    }

    @GetMapping("/status/{status}")
    public List<Message> getMessagesByStatus(@PathVariable String status) {
        return messageService.getMessagesByStatus(status);
    }

    @PutMapping("/status/{messageId}")
    public Optional<Message> updateMessageStatus(@PathVariable Long messageId, @RequestParam String status) {
        return messageService.updateMessageStatus(messageId, status);
    }
}
