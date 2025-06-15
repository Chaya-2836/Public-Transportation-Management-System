package com.example.javaProject.Services;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.javaProject.Convertors.TravelConvert;
import com.example.javaProject.Dto.TravelDto;
import com.example.javaProject.Models.Line;
import com.example.javaProject.Models.Travel;
import com.example.javaProject.Repositories.LineRepository;
import com.example.javaProject.Repositories.TravelRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Service
public class TravelService {
    @Autowired
    public TravelRepository travelRepository;
    @Autowired
    public TravelConvert travelConvert;
    @Autowired
    public LineRepository lineRepository;

    public List<TravelDto> getAllTravels() {
        return travelConvert.toTravelDtoList(travelRepository.findAll());
    }

    public boolean createTravel(TravelDto travelDto) {
        Travel travel = travelConvert.toModel(travelDto);
        if (!travelRepository.exists(Example.of(travel))) {
            travelRepository.save(travel);
            return true;
        }
        return false;
    }

    public List<TravelDto> getTravelByLineNumber(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new EntityNotFoundException("Line with number " + lineNumber + " not found"));
        if (line != null) {
            return travelConvert.toTravelDtoList(travelRepository.findByLine_id(line.getId()));
        }
        return List.of(); // מחזיר רשימה ריקה אם הקו לא נמצא
    }

    public List<LineTimeDto> getByLineAndTime(String number, LocalTime time) {
        Line line = lineRepository.findByNumber(number)
                .orElseThrow(() -> new EntityNotFoundException("Line with number " + number + " not found"));

        LocalTime startTime = time.minusMinutes(30);
        LocalTime endTime = time.plusHours(1);

        List<Travel> travels = travelRepository.findByLineIdAndDepartureTimeBetween(
                line.getId(), startTime, endTime);

        return travels.stream()
                .map(travel -> {
                    LineTimeDto dto = new LineTimeDto();
                    dto.setLineNumber(line.getNumber());
                    dto.setDepartureTime(travel.getDepartureTime().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public TravelDto getLastTravelForLine(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new EntityNotFoundException("Line with number " + lineNumber + " not found"));
        if (line == null)
            return null;

        Travel lastTravel = travelRepository.findFirstByLineIdOrderByDepartureTimeDesc(line.getId());
        return lastTravel != null ? travelConvert.toDto(lastTravel) : null;
    }

    // DTO פנימי
    @Data
    public static class LineTimeDto {
        private String lineNumber;
        private String departureTime;
    }
}
