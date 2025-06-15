package com.example.javaProject.Convertors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.javaProject.Dto.StationDto;
import com.example.javaProject.Models.Station;
import com.example.javaProject.Models.Station_Line;
import com.example.javaProject.Repositories.LineRepository;

@Component
public class StationConvert {

    @Autowired
    private LineRepository lineRepository;

    public StationDto toDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        stationDto.setPhone(station.getPhone());

        stationDto.setLinesIds(
                station.getStationLines().stream()
                        .map(sl -> sl.getLine().getId())
                        .collect(Collectors.toList()));

        return stationDto;
    }

    public Station toModelForCreate(StationDto stationDto) {
        Station station = toModelCommon(stationDto);
        // לא מוסיפים קווים כאן! התחנה נוצרת ללא קווים
        return station;
    }

    public Station toModelForUpdate(StationDto stationDto) {
        Station station = toModelCommon(stationDto);
        station.setId(stationDto.getId());

        if (stationDto.getLinesIds() != null && !stationDto.getLinesIds().isEmpty()) {
            List<Station_Line> stationLines = stationDto.getLinesIds().stream()
                    .map(lineId -> {
                        Station_Line sl = new Station_Line();
                        sl.setStation(station);
                        sl.setLine(lineRepository.getById(lineId));
                        return sl;
                    })
                    .collect(Collectors.toList());

            station.setStationLines(stationLines);
        }

        return station;
    }

    private Station toModelCommon(StationDto stationDto) {
        Station station = new Station();
        station.setName(stationDto.getName());
        station.setPhone(stationDto.getPhone());
        // לא נוגעים ב־stationLines כאן
        return station;
    }

    public List<StationDto> toStationDtoList(List<Station> stations) {
        return stations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Station> toStationList(List<StationDto> stationDtos) {
        return stationDtos.stream()
                .map(this::toModelForCreate)
                .collect(Collectors.toList());
    }
}
