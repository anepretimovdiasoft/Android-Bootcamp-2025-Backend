package com.example.edu.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private PlaceDTO place;
}
