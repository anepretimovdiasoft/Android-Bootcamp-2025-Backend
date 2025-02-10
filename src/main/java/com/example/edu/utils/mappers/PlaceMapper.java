package com.example.edu.utils.mappers;

import com.example.edu.dto.PlaceDTO;
import com.example.edu.entity.Place;
import com.example.edu.utils.GlobalUtils;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

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

    public List<PlaceDTO> convertAllToDTO(Collection<Place> persons) {
        return GlobalUtils.convertAllToDTO(persons, PlaceMapper::convertToDTO);
    }
}
