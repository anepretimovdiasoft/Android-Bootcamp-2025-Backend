package com.example.bootcamp.service;

import com.example.bootcamp.dto.ProfilesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfilesService {
    List<ProfilesDTO> getAllProfiles();
    ProfilesDTO getProfileById(Long id);
    ProfilesDTO createProfile(ProfilesDTO dto);
    ProfilesDTO updateProfile(Long id, ProfilesDTO dto);
    void deleteProfile(Long id);
}
