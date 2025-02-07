package com.example.bootcamp.util;

import com.example.bootcamp.dto.StatusesDTO;
import com.example.bootcamp.entity.Status;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StatusesMapper {
    public static StatusesDTO convertDTO(Status status) {
        StatusesDTO statusesDTO = new StatusesDTO();
        statusesDTO.setId(status.getId());
        statusesDTO.setStatus(status.getStatus());
        return statusesDTO;
    }
}
