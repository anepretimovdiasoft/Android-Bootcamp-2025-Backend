package com.example.bootcamp.service;

import com.example.bootcamp.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message sendMessage(Long userId, String content, Boolean isAdminMessage, Boolean isNotification);
    List<Message> getMessagesByUserId(Long userId);
    List<Message> getMessagesByStatus(String status);
    Optional<Message> updateMessageStatus(Long messageId, String newStatus);
}
