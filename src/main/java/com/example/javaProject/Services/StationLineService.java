package com.example.javaProject.Services;

import com.example.javaProject.Controllers.StationLineController.StationInfoDto;
import com.example.javaProject.Convertors.Station_LineConvert;
import com.example.javaProject.Dto.station_LinesDto;
import com.example.javaProject.Models.Line;
import com.example.javaProject.Models.Station;
import com.example.javaProject.Models.Station_Line;
import com.example.javaProject.Repositories.LineRepository;
import com.example.javaProject.Repositories.StationRepository;
import com.example.javaProject.Repositories.Station_LineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationLineService {

    @Autowired
    private Station_LineRepository station_LineRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private Station_LineConvert stationLineConvert;

    public station_LinesDto addStationToLine(String lineNumber, String stationPhone, Integer order) {
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new EntityNotFoundException("Line with number " + lineNumber + " not found"));

        Station station = stationRepository.findByPhone(stationPhone)
                .orElseThrow(() -> new EntityNotFoundException("Station with phone " + stationPhone + " not found"));

        if (station_LineRepository.existsByLineIdAndStationId(line.getId(), station.getId())) {
            throw new IllegalArgumentException("Station already exists in the line");
        }

        List<Station_Line> stationLines = station_LineRepository.findByLineIdOrderByStationOrderAsc(line.getId());
        for (Station_Line sl : stationLines) {
            if (sl.getStationOrder() >= order.intValue()) {
                sl.setStationOrder(sl.getStationOrder() + 1);
            }
        }

        Station_Line newStationLine = new Station_Line();
        newStationLine.setLine(line);
        newStationLine.setStation(station);
        newStationLine.setStationOrder(order.intValue());

        stationLines.add(newStationLine);
        station_LineRepository.saveAll(stationLines);

        return stationLineConvert.toDto(newStationLine);
    }

    public void removeStationFromLine(String lineNumber, String stationPhone) {
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new EntityNotFoundException("Line with number " + lineNumber + " not found"));

        Station station = stationRepository.findByPhone(stationPhone)
                .orElseThrow(() -> new EntityNotFoundException("Station with phone " + stationPhone + " not found"));

        Station_Line toDelete = station_LineRepository
                .findByLineIdAndStationId(line.getId(), station.getId())
                .orElseThrow(() -> new EntityNotFoundException("Relation not found"));

        int deletedOrder = toDelete.getStationOrder();
        station_LineRepository.delete(toDelete);

        List<Station_Line> stationLines = station_LineRepository.findByLineIdOrderByStationOrderAsc(line.getId());
        for (Station_Line sl : stationLines) {
            if (sl.getStationOrder() > deletedOrder) {
                sl.setStationOrder(sl.getStationOrder() - 1);
            }
        }

        station_LineRepository.saveAll(stationLines);
    }

    public List<station_LinesDto> getStationsOfLine(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new EntityNotFoundException("Line with number " + lineNumber + " not found"));

        List<Station_Line> stationLines = station_LineRepository.findByLineIdOrderByStationOrderAsc(line.getId());
        return stationLineConvert.toDtoList(stationLines);
    }

    public List<StationInfoDto> getStationInfosByLine(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new EntityNotFoundException("Line with number " + lineNumber + " not found"));

        List<Station_Line> stationLines = station_LineRepository.findByLineIdOrderByStationOrderAsc(line.getId());

        return stationLines.stream()
                .map(sl -> {
                    Station station = sl.getStation();
                    return new StationInfoDto(station.getName(), station.getPhone());
                })
                .collect(Collectors.toList());
    }
}
