package com.example.javaProject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.javaProject.Convertors.StationConvert;
import com.example.javaProject.Dto.StationDto;
import com.example.javaProject.Models.Station;
import com.example.javaProject.Repositories.StationRepository;

@Service
public class StationService {
    @Autowired
    public StationRepository stationRepository;

    @Autowired
    public StationConvert stationConvert;

    public List<StationDto> getAllStations() {
        return stationConvert.toStationDtoList(stationRepository.findAll());
    }

    public boolean createStation(StationDto stationDto) {
        Station station = stationConvert.toModelForCreate(stationDto);
        if (!stationRepository.exists(Example.of(station))) {
            stationRepository.save(station);
            return true;
        }
        return false;
    }

    public boolean updateStation(Long id, StationDto updatedDto) {
        return stationRepository.findById(id).map(existing -> {
            Station updatedStation = stationConvert.toModelForUpdate(updatedDto);
            updatedStation.setId(id); // חשוב!
            stationRepository.save(updatedStation);
            return true;
        }).orElse(false);
    }

}
