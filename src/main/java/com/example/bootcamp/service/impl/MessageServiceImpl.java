package com.example.bootcamp.service.impl;

import com.example.bootcamp.entity.Message;
import com.example.bootcamp.exception.MessageNotFoundException;
import com.example.bootcamp.repository.MessageRepository;
import com.example.bootcamp.service.MessageService;
import com.example.bootcamp.util.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message sendMessage(Long userId, String content, Boolean isAdminMessage, Boolean isNotification) {
        Message message = new Message();
//        message.setUserId(userId);
        message.setContent(content);
        message.setIsAdminMessage(isAdminMessage);
        message.setIsNotification(isNotification);
        message.setStatus("sent");
        message.setCreatedAt(LocalDateTime.now());

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepository.findByUserId(userId);
    }

    @Override
    public List<Message> getMessagesByStatus(String status) {
        if (!MessageMapper.isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        return messageRepository.findByStatus(status);
    }

    @Override
    public Optional<Message> updateMessageStatus(Long messageId, String newStatus) {
        if (!MessageMapper.isValidStatus(newStatus)) {
            throw new IllegalArgumentException("Invalid status: " + newStatus);
        }

        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setStatus(newStatus);
            return Optional.of(messageRepository.save(message));
        } else {
            throw new MessageNotFoundException("Message with ID " + messageId + " not found");
        }
    }
}
