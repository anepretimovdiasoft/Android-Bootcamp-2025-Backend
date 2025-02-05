package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.ProfilesDTO;
import com.example.bootcamp.modal.Profile;
import com.example.bootcamp.repository.ProfileRepository;
import com.example.bootcamp.service.ProfilesService;
import com.example.bootcamp.util.ProfilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    private final ProfileRepository profilesRepository;

    @Autowired
    public ProfilesServiceImpl(ProfileRepository profilesRepository) {
        this.profilesRepository = profilesRepository;
    }

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
    public ProfilesDTO createProfile(ProfilesDTO dto) {
        Profile profile = new Profile();
        profile.setCenter(dto.getCenterId());
        profile.setName(dto.getName());
        profile.setLastname(dto.getLastname());
        profile.setAge(dto.getAge());
        profile.setPicture(dto.getPicture());
        profile.setBio(dto.getBio());

        Profile savedProfile = profilesRepository.save(profile);
        return ProfilesMapper.convertDTO(savedProfile);
    }

    @Override
    public ProfilesDTO updateProfile(Long id, ProfilesDTO dto) {
        Optional<Profile> existingProfileOptional = profilesRepository.findById(id);
        if (existingProfileOptional.isPresent()) {
            Profile existingProfile = existingProfileOptional.get();
            existingProfile.setCenter(dto.getCenterId());
            existingProfile.setName(dto.getName());
            existingProfile.setLastname(dto.getLastname());
            existingProfile.setAge(dto.getAge());
            existingProfile.setPicture(dto.getPicture());
            existingProfile.setBio(dto.getBio());
            Profile updatedProfile = profilesRepository.save(existingProfile);
            return ProfilesMapper.convertDTO(updatedProfile);
        }
        return null;
    }

    @Override
    public void deleteProfile(Long id) {
        profilesRepository.deleteById(id);
    }
}
