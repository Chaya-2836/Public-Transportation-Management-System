package com.example.javaProject.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javaProject.Models.Line;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
    Optional<Line> findByNumber(String number);
}
