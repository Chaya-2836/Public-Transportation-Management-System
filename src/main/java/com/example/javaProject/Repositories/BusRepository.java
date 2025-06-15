package com.example.javaProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javaProject.Models.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}
