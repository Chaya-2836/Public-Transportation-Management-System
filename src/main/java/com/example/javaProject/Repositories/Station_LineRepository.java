package com.example.javaProject.Repositories;

import com.example.javaProject.Models.Station_Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Station_LineRepository extends JpaRepository<Station_Line, Long> {
    List<Station_Line> findByLineIdOrderByStationOrderAsc(Long lineId);

    Optional<Station_Line> findByLineIdAndStationId(Long lineId, Long stationId);

    boolean existsByLineIdAndStationId(Long lineId, Long stationId);

}
