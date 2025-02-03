package com.example.bootcamp.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterDTO {
    private long id;
    private String name;
    private String description;
    private String address;
    private String linkLogo;
    private Double latitude;
    private Double longitude;
    private String vkLink;
    private String linkSite;

    private List<Long> volunteers;  // Список id всех волонтеров, которые работают в центре.
    private List<Long> events;  // Список id всех событий, которые проводит центр
}
