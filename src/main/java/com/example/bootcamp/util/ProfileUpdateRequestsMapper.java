package com.example.bootcamp.util;

import com.example.bootcamp.dto.ProfileUpdateRequestsDTO;
import com.example.bootcamp.modal.ProfileUpdateRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfileUpdateRequestsMapper {
    public static ProfileUpdateRequestsDTO convertDTO(ProfileUpdateRequest request) {
        ProfileUpdateRequestsDTO profileUpdateRequestsDTO = new ProfileUpdateRequestsDTO();
        profileUpdateRequestsDTO.setId(request.getId());
        profileUpdateRequestsDTO.setUserId(request.getUser());
        profileUpdateRequestsDTO.setNewProfileId(request.getNewProfile());
        profileUpdateRequestsDTO.setOldProfileId(request.getOldProfile());
        profileUpdateRequestsDTO.setStatusId(request.getStatus());
        return profileUpdateRequestsDTO;
    }
}
