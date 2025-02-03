package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Volunteer_centers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CenterRepository extends JpaRepository<Volunteer_centers, Long> {
    Optional<Volunteer_centers> findByTitle(String name);
}
