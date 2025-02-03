package com.example.bootcamp.repository;


import com.example.bootcamp.entity.Volunteer_status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Volunteer_status, Long> {
    Optional<Volunteer_status> findById(long id);
}
