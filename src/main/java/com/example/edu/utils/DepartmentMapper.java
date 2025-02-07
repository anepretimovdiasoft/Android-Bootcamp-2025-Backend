package com.example.edu.utils;

import com.example.edu.dto.DepartmentDTO;
import com.example.edu.entity.Department;
import com.example.edu.entity.Place;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DepartmentMapper {
    public DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        System.out.println("department id: " + department.getId());

        dto.setId(department.getId());
        dto.setName(department.getName());

        Place place = department.getPlace();
        System.out.println("place id: " + place.getId());
        dto.setPlace(PlaceMapper.convertToDTO(place));

        return dto;
    }
}
