package com.example.bootcamp.service;

import com.example.bootcamp.dto.ProfileUpdateRequestsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileUpdateRequestsService {
    List<ProfileUpdateRequestsDTO> getAllProfileUpdateRequests();
    ProfileUpdateRequestsDTO getProfileUpdateRequestById(Long id);
    ProfileUpdateRequestsDTO createProfileUpdateRequest(ProfileUpdateRequestsDTO dto);
    ProfileUpdateRequestsDTO updateProfileUpdateRequest(Long id, ProfileUpdateRequestsDTO dto);
    void deleteProfileUpdateRequest(Long id);
}
