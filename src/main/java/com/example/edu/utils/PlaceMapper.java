package com.example.edu.utils;

import com.example.edu.dto.PlaceDTO;
import com.example.edu.entity.Place;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlaceMapper {
    public PlaceDTO convertToDTO(Place place) {
        PlaceDTO dto = new PlaceDTO();

        dto.setId(place.getId());
        dto.setName(place.getName());
        dto.setAddress(place.getAddress());
        dto.setInformation(place.getInformation());
        dto.setPathToImage(place.getPathToImage());
        dto.setLatLng(place.getLatLng());

        return dto;
    }
}
