package com.example.bootcamp.util.Mapper;

import com.example.bootcamp.dto.CenterDTO;
import com.example.bootcamp.entity.Center;
import com.example.bootcamp.util.CalculateDistance.CalculateDistance;
import lombok.experimental.UtilityClass;
import org.springframework.data.geo.Distance;

import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class CenterMapper {
    public CenterDTO convertToDTO(Center center) {
        CenterDTO centerDTO = new CenterDTO();
        centerDTO.setId(center.getId());
        centerDTO.setName(center.getName());
        centerDTO.setDescription(center.getDescription());
        centerDTO.setUsers(center.getUsers().stream().map(UserMapper::convertToDTO).collect(Collectors.toList()));
        if (center.getDistance() != null) {
            centerDTO.setDistance(center.getDistance());
        }
        centerDTO.setLat(center.getLat());
        centerDTO.setLng(center.getLng());
        return centerDTO;
    }

    public Center addDistance(Center center, double latitude, double longitude, double latitude2, double longitude2) {
        center.setDistance(CalculateDistance.calculateDistance(latitude, longitude, latitude2, longitude2));
        return center;
    }
}
