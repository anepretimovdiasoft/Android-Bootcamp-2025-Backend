package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Person;
import com.example.bootcamp.entity.VolunteerCentre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerCentreRepository extends JpaRepository<VolunteerCentre, Long> {
    Optional<VolunteerCentre> findByName(String name);

    @Override
    Page<VolunteerCentre> findAll(Pageable pageable);
}
