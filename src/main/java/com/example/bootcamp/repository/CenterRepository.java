package com.example.bootcamp.repository;

import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.VolunteerCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CenterRepository extends JpaRepository<VolunteerCenter, Long> {
    Optional<VolunteerCenter> findByName(String name);

    @Override
    Page<VolunteerCenter> findAll(Pageable pageable);

    // @EntityGraph(attributePaths = {"users", "authorities"})
    List<VolunteerCenter> findAll();
}
