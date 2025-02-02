package com.example.bootcamp.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private long id;
    private String name;
    private String description;
    private String imageLink;
    private String address;
    private Double latitude;
    private Double longitude;
    private long center;
}
