package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @EntityGraph(attributePaths = {"volunteer", "authorities"})
    List<Person> findAll();

    Optional<Person> findByUsername(String username);

    @Override
    Page<Person> findAll(Pageable pageable);
}
