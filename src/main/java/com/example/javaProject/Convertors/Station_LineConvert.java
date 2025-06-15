package com.example.javaProject.Convertors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.javaProject.Dto.station_LinesDto;
import com.example.javaProject.Models.Station_Line;
import com.example.javaProject.Repositories.LineRepository;
import com.example.javaProject.Repositories.StationRepository;

@Component
public class Station_LineConvert {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LineRepository lineRepository;

    public station_LinesDto toDto(Station_Line stationLine) {
        station_LinesDto dto = new station_LinesDto();
        dto.setLineStationId(stationLine.getLine_id_Station_id());
        dto.setStationId(stationLine.getStation().getId());
        dto.setLineId(stationLine.getLine().getId());
        dto.setStationOrder(stationLine.getStationOrder());
        return dto;
    }

    public Station_Line toModel(station_LinesDto dto) {
        Station_Line stationLine = new Station_Line();
        stationLine.setLine_id_Station_id(dto.getLineStationId());
        stationLine.setStation(stationRepository.getById(dto.getStationId()));
        stationLine.setLine(lineRepository.getById(dto.getLineId()));
        stationLine.setStationOrder(dto.getStationOrder());
        return stationLine;
    }

    public List<station_LinesDto> toDtoList(List<Station_Line> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Station_Line> toModelList(List<station_LinesDto> list) {
        return list.stream().map(this::toModel).collect(Collectors.toList());
    }
}
