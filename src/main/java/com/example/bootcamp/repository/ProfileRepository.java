package com.example.bootcamp.repository;

import com.example.bootcamp.modal.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByCenterId(Integer centerId);
    Optional<Profile> findByNameAndLastname(String name, String lastname);
}
