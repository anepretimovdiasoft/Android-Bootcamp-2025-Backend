package com.example.bootcamp.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private long id;
    private long toWhom;  // id принимающего
    private long fromWhom;  // id отправителя
    private String message;
    private LocalDate dateDispatch;
}
