package com.example.bootcamp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CentersDTO {

    private long id;
    private String name;
    private List<Integer> volunteerIds;
    private String address;
    private float latitude;
    private float longitude;
    private LocalDateTime created;
    private LocalDateTime updated;
}
