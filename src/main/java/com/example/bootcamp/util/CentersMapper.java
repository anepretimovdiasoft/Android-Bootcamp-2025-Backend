package com.example.bootcamp.util;

import com.example.bootcamp.dto.CentersDTO;
import com.example.bootcamp.modal.Center;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CentersMapper {
    public static CentersDTO convertDTO(Center center) {
        CentersDTO centersDTO = new CentersDTO();
        centersDTO.setId(center.getId());
        centersDTO.setName(center.getName());
        centersDTO.setVolunteerIds(center.getVolunteerIds());
        centersDTO.setAddress(center.getAddress());
        centersDTO.setLatitude(center.getLatitude());
        centersDTO.setLongitude(center.getLongitude());
        centersDTO.setCreated(center.getCreated());
        centersDTO.setUpdated(center.getUpdated());
        return centersDTO;
    }
}
