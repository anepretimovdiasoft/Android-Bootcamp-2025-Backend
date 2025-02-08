package com.example.edu.utils.mappers;

import com.example.edu.dto.department.DepartmentDTO;
import com.example.edu.entity.Department;
import com.example.edu.entity.Place;
import com.example.edu.utils.GlobalUtils;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class DepartmentMapper {
    public DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();

        dto.setId(department.getId());
        dto.setName(department.getName());

        Place place = department.getPlace();
        dto.setPlace(PlaceMapper.convertToDTO(place));

        return dto;
    }

    public List<DepartmentDTO> convertAllToDTO(Collection<Department> departments) {
        return GlobalUtils.convertAllToDTO(departments, DepartmentMapper::convertToDTO);
    }
}
