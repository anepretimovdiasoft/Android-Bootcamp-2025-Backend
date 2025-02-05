package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Volunteer_center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Volunteer_centerRepository extends JpaRepository<Volunteer_center, Long> {
    Optional<Volunteer_center> findByVolunteer_centerId(Long id);

}
