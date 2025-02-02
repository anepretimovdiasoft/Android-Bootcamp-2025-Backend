package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CenterRepository extends JpaRepository<Center, Long> {
    Optional<Center> findByName(String name);

}
