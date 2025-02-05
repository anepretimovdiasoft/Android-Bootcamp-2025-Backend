package com.example.bootcamp.repository;

import com.example.bootcamp.entity.VolunteerCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolunteerCenterRepository extends JpaRepository<VolunteerCenter, Long> {
    Optional<VolunteerCenter> findByName(String name);
}
