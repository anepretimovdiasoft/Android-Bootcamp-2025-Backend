package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Volunteer_centers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CenterRepository extends JpaRepository<Volunteer_centers, Long> {
    Optional<Volunteer_centers> findByTitle(String name);
    Optional<Volunteer_centers> findById(Long centers_id);

}
