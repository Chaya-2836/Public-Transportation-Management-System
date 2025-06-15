package com.example.javaProject.Convertors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.javaProject.Dto.LineDto;
import com.example.javaProject.Models.Line;
import com.example.javaProject.Models.Station_Line;
import com.example.javaProject.Repositories.StationRepository;

@Component
public class LineConverter {

    @Autowired
    private StationRepository stationRepository;

    public LineDto toDto(Line line) {
        LineDto lineDto = new LineDto();
        lineDto.setId(line.getId());
        lineDto.setNumber(line.getNumber());
        lineDto.setSource(line.getSource());
        lineDto.setDestination(line.getDestination());

        lineDto.setStationsIds(
                line.getStationLines().stream()
                        .map(sl -> sl.getStation().getId())
                        .collect(Collectors.toList()));

        return lineDto;
    }

    public Line toModel(LineDto lineDto) {
        Line line = new Line();
        line.setId(lineDto.getId());
        line.setNumber(lineDto.getNumber());
        line.setSource(lineDto.getSource());
        line.setDestination(lineDto.getDestination());

        if (lineDto.getStationsIds() != null && !lineDto.getStationsIds().isEmpty()) {
            List<Station_Line> stationLines = lineDto.getStationsIds().stream()
                    .map(stationId -> {
                        Station_Line sl = new Station_Line();
                        sl.setLine(line);
                        sl.setStation(stationRepository.getById(stationId));
                        return sl;
                    })
                    .collect(Collectors.toList());

            line.setStationLines(stationLines);
        } else {
            line.setStationLines(List.of());
        }

        return line;
    }

    public List<LineDto> toLineDtoList(List<Line> lines) {
        return lines.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Line> toLinesList(List<LineDto> lineDtos) {
        return lineDtos.stream().map(this::toModel).collect(Collectors.toList());
    }
}
