package com.example.bootcamp.util;

import com.example.bootcamp.dto.CentersDTO;
import com.example.bootcamp.dto.FullCentersDTO;
import com.example.bootcamp.entity.Center;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class FullCentersMapper {
    public static FullCentersDTO convertDTO(Center center) {
        System.out.println("Converting Center to FullCentersDTO...");
        FullCentersDTO fullCentersDTO = new FullCentersDTO();
        fullCentersDTO.setId(center.getId());
        fullCentersDTO.setType(center.getType());
        if (!center.getVolunteer_ids().isEmpty()) {
            fullCentersDTO.setVolunteerIds(center.getVolunteer_ids());
        }
        fullCentersDTO.setName(center.getName());
        fullCentersDTO.setDescription(center.getDescription());
        fullCentersDTO.setAddress(center.getAddress());
        fullCentersDTO.setPhone(center.getPhone());
        fullCentersDTO.setEmail(center.getEmail());
        fullCentersDTO.setLink(center.getLink());

        System.out.println("Tags: " + center.getTags());

        fullCentersDTO.setImageUrl(center.getImageUrl());
        return fullCentersDTO;
    }

}
