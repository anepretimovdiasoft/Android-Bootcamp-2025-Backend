package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Volunteer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    @Override
    @EntityGraph(attributePaths = {"volunteerCenter", "authorities"})
    List<Volunteer> findAll();

    @Override
    Page<Volunteer> findAll(Pageable pageable);

    Optional<Volunteer> findByUsername(String username);

    Optional<Volunteer> findByName(String name);
}
