package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.ProfilesDTO;
import com.example.bootcamp.entity.Center;
import com.example.bootcamp.entity.Profile;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.ProfileRepository;
import com.example.bootcamp.service.ProfilesService;
import com.example.bootcamp.util.ProfilesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {

    private final ProfileRepository profilesRepository;
    private final CenterRepository centerRepository;

    @Override
    public List<ProfilesDTO> getAllProfiles() {
        return profilesRepository.findAll().stream()
                .map(ProfilesMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfilesDTO getProfileById(Long id) {
        Optional<Profile> profile = profilesRepository.findById(id);
        return profile.map(ProfilesMapper::convertDTO).orElse(null);
    }


    @Override
    public String updateProfile(Long id, ProfilesDTO dto) {
        Optional<Profile> existingProfileOptional = profilesRepository.findById(id);
        if (existingProfileOptional.isPresent()) {
            Profile existingProfile = existingProfileOptional.get();
            Optional<Center> centerOptional = centerRepository.findById(dto.getCenterId());

            if (centerOptional.isPresent()) {
                Center center = centerOptional.get();
                existingProfile.setCenter(center);
            }

            if (dto.getName() != null && !dto.getName().isEmpty()) {
                existingProfile.setName(dto.getName());
            }
            if (dto.getLastname() != null && !dto.getLastname().isEmpty()) {
                existingProfile.setLastname(dto.getLastname());
            }
            if (dto.getAge() != null) {
                existingProfile.setAge(dto.getAge());
            }
            if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
                existingProfile.setEmail(dto.getEmail());
            }
            if (dto.getPhone() != null && !dto.getPhone().isEmpty()) {
                existingProfile.setPhone(dto.getPhone());
            }
            if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
                existingProfile.setPicture(dto.getPicture());
            }

            profilesRepository.save(existingProfile);

            return "Done";
        }
        return null;
    }

}
