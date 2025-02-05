package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.ProfileUpdateRequestsDTO;
import com.example.bootcamp.modal.ProfileUpdateRequest;
import com.example.bootcamp.repository.ProfileUpdateRequestRepository;
import com.example.bootcamp.service.ProfileUpdateRequestsService;
import com.example.bootcamp.util.ProfileUpdateRequestsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileUpdateRequestsServiceImpl implements ProfileUpdateRequestsService {

    private final ProfileUpdateRequestRepository profileUpdateRequestsRepository;

    @Autowired
    public ProfileUpdateRequestsServiceImpl(ProfileUpdateRequestRepository profileUpdateRequestsRepository) {
        this.profileUpdateRequestsRepository = profileUpdateRequestsRepository;
    }

    @Override
    public List<ProfileUpdateRequestsDTO> getAllProfileUpdateRequests() {
        return profileUpdateRequestsRepository.findAll().stream()
                .map(ProfileUpdateRequestsMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileUpdateRequestsDTO getProfileUpdateRequestById(Long id) {
        Optional<ProfileUpdateRequest> request = profileUpdateRequestsRepository.findById(id);
        return request.map(ProfileUpdateRequestsMapper::convertDTO).orElse(null);
    }

    @Override
    public ProfileUpdateRequestsDTO createProfileUpdateRequest(ProfileUpdateRequestsDTO dto) {
        ProfileUpdateRequest request = new ProfileUpdateRequest();
        request.setUser(dto.getUserId());
        request.setNewProfile(dto.getNewProfileId());
        request.setOldProfile(dto.getOldProfileId());
        request.setStatus(dto.getStatusId());

        ProfileUpdateRequest savedRequest = profileUpdateRequestsRepository.save(request);

        ProfileUpdateRequestsDTO responseDto = new ProfileUpdateRequestsDTO();
        responseDto.setUserId(savedRequest.getUser());
        responseDto.setNewProfileId(savedRequest.getNewProfile());
        responseDto.setOldProfileId(savedRequest.getOldProfile());
        responseDto.setStatusId(savedRequest.getStatus());

        return responseDto;
    }


    @Override
    public ProfileUpdateRequestsDTO updateProfileUpdateRequest(Long id, ProfileUpdateRequestsDTO dto) {
        Optional<ProfileUpdateRequest> existingRequestOptional = profileUpdateRequestsRepository.findById(id);
        if (existingRequestOptional.isPresent()) {
            ProfileUpdateRequest existingRequest = existingRequestOptional.get();
            existingRequest.setUser(dto.getUserId());
            existingRequest.setNewProfile(dto.getNewProfileId());
            existingRequest.setOldProfile(dto.getOldProfileId());
            existingRequest.setStatus(dto.getStatusId());
            ProfileUpdateRequest updatedRequest = profileUpdateRequestsRepository.save(existingRequest);
            return ProfileUpdateRequestsMapper.convertDTO(updatedRequest);
        }
        return null;
    }

    @Override
    public void deleteProfileUpdateRequest(Long id) {
        profileUpdateRequestsRepository.deleteById(id);
    }
}
