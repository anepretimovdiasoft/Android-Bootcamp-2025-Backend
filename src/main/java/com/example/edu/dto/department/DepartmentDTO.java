package com.example.edu.dto.department;

import com.example.edu.dto.PlaceDTO;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private PlaceDTO place;
}
