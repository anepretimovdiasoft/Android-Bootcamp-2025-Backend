package com.example.bootcamp.dto;

import lombok.Data;
import java.util.List;

@Data
public class CenterDTO {
    private Long id;
    private String name;
    private String description;
    private Double distance;
    private double lat;
    private double lng;
    private List<UserDTO> users;
}
