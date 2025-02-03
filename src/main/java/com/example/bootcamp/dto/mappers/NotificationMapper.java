package com.example.bootcamp.dto.mappers;

import com.example.bootcamp.domain.entity.Notification;
import com.example.bootcamp.domain.entity.Volunteer;
import com.example.bootcamp.dto.entity.NotificationDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationMapper {
    public static NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setId(notification.getId());
        notificationDTO.setToWhom(notification.getToWhom().getId());
        notificationDTO.setFromWhom(notification.getFromWhom().getId());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setDateDispatch(notification.getDateDispatch());

        return notificationDTO;
    }

    public static Notification convertFromDTO(NotificationDTO notificationDTO, Volunteer toWhom, Volunteer fromWhom) {
        Notification notification = new Notification();

        notification.setToWhom(toWhom);
        notification.setFromWhom(fromWhom);
        notification.setMessage(notificationDTO.getMessage());
        notification.setDateDispatch(notificationDTO.getDateDispatch());

        return notification;
    }
}
