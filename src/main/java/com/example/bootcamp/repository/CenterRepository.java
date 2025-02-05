package com.example.bootcamp.repository;

import com.example.bootcamp.modal.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    List<Center> findByName(String name);
    List<Center> findByLatitudeAndLongitude(Float latitude, Float longitude);
}