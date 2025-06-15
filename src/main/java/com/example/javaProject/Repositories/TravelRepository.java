package com.example.javaProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javaProject.Models.Travel;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByLine_id(Long lineId);

    List<Travel> findByLineIdAndDepartureTime(Long lineId, LocalTime departureTime);

    List<Travel> findByLineIdAndDepartureTimeBetween(Long lineId, LocalTime start, LocalTime end);

    Travel findFirstByLineIdOrderByDepartureTimeDesc(Long lineId);

}
