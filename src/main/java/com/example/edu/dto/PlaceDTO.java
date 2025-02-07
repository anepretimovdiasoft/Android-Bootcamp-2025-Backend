package com.example.edu.dto;
import lombok.Data;

@Data
public class PlaceDTO {
    private Long id;
    private String name;
    private String pathToImage;
    private String address;
    private String information;
    private String latLng;
}
