package com.example.javaProject.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javaProject.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByPhone(String phone);

}
