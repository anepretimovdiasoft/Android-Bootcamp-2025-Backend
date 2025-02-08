package com.example.bootcamp.repository;

import com.example.bootcamp.entity.ProfileUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileUpdateRequestRepository extends JpaRepository<ProfileUpdateRequest, Long> {
    Optional<ProfileUpdateRequest> findByUserId(Integer userId);
    Optional<ProfileUpdateRequest> findByNewProfileId(Integer newProfileId);
    Optional<ProfileUpdateRequest> findByOldProfileId(Integer oldProfileId);
}
