package com.example.bootcamp.dto;

import com.example.bootcamp.modal.Profile;
import com.example.bootcamp.modal.Status;
import com.example.bootcamp.modal.Users;
import lombok.Data;

@Data
public class ProfileUpdateRequestsDTO {

    private long id;
    private Users userId;
    private Profile newProfileId;
    private Profile oldProfileId;
    private Status statusId;
}
