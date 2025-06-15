package com.example.javaProject.Convertors;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.javaProject.Dto.TravelDto;
import com.example.javaProject.Models.Travel;
import com.example.javaProject.Repositories.BusRepository;
import com.example.javaProject.Repositories.DriverRepository;
import com.example.javaProject.Repositories.LineRepository;

@Component
public class TravelConvert {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LineRepository lineRepository;

    public TravelDto toDto(Travel travel) {
        TravelDto travelDto = new TravelDto();
        travelDto.setId(travel.getId());
        travelDto.setBusId(travel.getBusId().getId());
        travelDto.setDriverId(travel.getDriverId().getId());
        travelDto.setLineId(travel.getLine().getId());

        if (travel.getDepartureTime() != null) {
            travelDto.setDepartureTime(travel.getDepartureTime().format(TIME_FORMATTER));
        }

        return travelDto;
    }

    public Travel toModel(TravelDto travelDto) {
        Travel travel = new Travel();
        travel.setId(travelDto.getId());
        travel.setBusId(busRepository.getById(travelDto.getBusId()));
        travel.setDriverId(driverRepository.getById(travelDto.getDriverId()));
        travel.setLine(lineRepository.getById(travelDto.getLineId()));

        if (travelDto.getDepartureTime() != null && !travelDto.getDepartureTime().isEmpty()) {
            travel.setDepartureTime(LocalTime.parse(travelDto.getDepartureTime(), TIME_FORMATTER));
        }

        return travel;
    }

    public List<TravelDto> toTravelDtoList(List<Travel> travels) {
        return travels.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Travel> toTravelList(List<TravelDto> travelsDto) {
        return travelsDto.stream().map(this::toModel).collect(Collectors.toList());
    }
}
