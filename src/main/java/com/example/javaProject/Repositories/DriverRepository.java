package com.example.javaProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javaProject.Models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
