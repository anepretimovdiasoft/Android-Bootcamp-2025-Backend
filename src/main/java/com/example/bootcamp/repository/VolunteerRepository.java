package com.example.bootcamp.repository;

import com.example.bootcamp.domain.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Optional<Volunteer> findByEmail(String email);
    Optional<Volunteer> findByTelephone(String telephone);
}
