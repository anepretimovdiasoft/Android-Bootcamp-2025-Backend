package com.example.bootcamp.dto;

import com.example.bootcamp.entity.Profile;
import com.example.bootcamp.entity.Status;
import com.example.bootcamp.entity.Users;
import lombok.Data;

@Data
public class ProfileUpdateRequestsDTO {

    private long id;
    private Users userId;
    private Profile newProfileId;
    private Profile oldProfileId;
    private Status statusId;
}
