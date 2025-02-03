package com.example.bootcamp.service.impl;

import com.example.bootcamp.aspect.annotation.LogExample;
import com.example.bootcamp.domain.entity.Notification;
import com.example.bootcamp.domain.entity.Volunteer;
import com.example.bootcamp.domain.exception.ResourceNotFoundException;
import com.example.bootcamp.dto.entity.NotificationDTO;
import com.example.bootcamp.dto.mappers.NotificationMapper;
import com.example.bootcamp.repository.NotificationRepository;
import com.example.bootcamp.repository.VolunteerRepository;
import com.example.bootcamp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    @LogExample
    public List<NotificationDTO> getAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public NotificationDTO getById(long id) {
        return notificationRepository.findById(id)
                .map(NotificationMapper::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Сообщение с id (" + id + ") не найдено!"));
    }

    @Override
    @LogExample
    public List<NotificationDTO> getAllBySenderId(long senderId) {
        return notificationRepository.findAll().stream()
                .filter(notification -> notification.getFromWhom().getId() == senderId)
                .sorted(Comparator.comparing(Notification::getDateDispatch))
                .map(NotificationMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public List<NotificationDTO> getAllByRecipientId(long recipientId) {
        return notificationRepository.findAll().stream()
                .filter(notification -> notification.getToWhom().getId() == recipientId)
                .sorted(Comparator.comparing(Notification::getDateDispatch))
                .map(NotificationMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public List<NotificationDTO> getAllVolunteer(long volunteerId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Волонтера с id (" + volunteerId + ") не существует!"));

        return Stream.concat(volunteer.getNotificationsFromWhom().stream(), volunteer.getNotificationsToWhom().stream())
                .sorted(Comparator.comparing(Notification::getDateDispatch))
                .map(NotificationMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public NotificationDTO create(NotificationDTO notificationDTO) {
        Volunteer toWhom = volunteerRepository.findById(notificationDTO.getToWhom())
                .orElseThrow(() -> new ResourceNotFoundException("Волонтера с id (" + notificationDTO.getToWhom() + ") не существует!"));
        Volunteer fromWhom = volunteerRepository.findById(notificationDTO.getFromWhom())
                .orElseThrow(() -> new ResourceNotFoundException("Волонтера с id (" + notificationDTO.getFromWhom() + ") не существует!"));

        return NotificationMapper.convertToDTO(
                notificationRepository.save(NotificationMapper.convertFromDTO(notificationDTO, toWhom, fromWhom))
        );
    }

    @Override
    @LogExample
    public NotificationDTO update(long id, NotificationDTO notificationDTO) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Уведомления с id (" + id + ") не существует!"));
        Volunteer toWhom = volunteerRepository.findById(notificationDTO.getToWhom())
                .orElseThrow(() -> new ResourceNotFoundException("Волонтера с id (" + notificationDTO.getToWhom() + ") не существует!"));
        Volunteer fromWhom = volunteerRepository.findById(notificationDTO.getFromWhom())
                .orElseThrow(() -> new ResourceNotFoundException("Волонтера с id (" + notificationDTO.getFromWhom() + ") не существует!"));

        notification.setToWhom(toWhom);
        notification.setFromWhom(fromWhom);
        notification.setMessage(notificationDTO.getMessage());
        notification.setDateDispatch(notificationDTO.getDateDispatch());

        return NotificationMapper.convertToDTO(notificationRepository.save(notification));
    }

    @Override
    @LogExample
    public void delete(long notificationId) {
        notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Сообщение с id (" + notificationId + ") не найдено!"));
        notificationRepository.deleteById(notificationId);
    }
}
