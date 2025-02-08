package com.example.bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FullCentersDTO {

    private long id;
    private String type;
    private String name;
    private String description;
    private String address;
    private String phone;
    private List<Integer> volunteerIds;
    private String email;
    private String link;
    @JsonProperty("tags")
    private List<String> tags;
    private String imageUrl;
    @JsonProperty("active")
    private List<Integer> active;
}
