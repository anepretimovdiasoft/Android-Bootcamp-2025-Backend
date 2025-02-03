package com.example.bootcamp.service;

import com.example.bootcamp.dto.entity.NotificationDTO;

import java.util.List;

public interface NotificationService {
    // Сортировка по дате отправки
    List<NotificationDTO> getAll();
    NotificationDTO getById(long id);
    List<NotificationDTO> getAllBySenderId(long senderId);
    List<NotificationDTO> getAllByRecipientId(long recipientId);
    List<NotificationDTO> getAllVolunteer(long volunteerId);

    NotificationDTO create(NotificationDTO notificationDTO);
    NotificationDTO update(long id, NotificationDTO notificationDTO);
    void delete(long notificationId);
}
