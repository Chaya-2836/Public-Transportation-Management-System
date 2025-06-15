package com.example.javaProject.Convertors;

import java.util.List;
import java.util.stream.Collectors;

import com.example.javaProject.Dto.BusDto;
import com.example.javaProject.Models.Bus;

public class BusConvert {

    public BusDto toDto(Bus bus) {
        BusDto busDto = new BusDto();
        busDto.setId(bus.getId());
        busDto.setLicensePlate(bus.getLicense_plate());
        busDto.setSeats(bus.getSeats());
        return busDto;
    }

    public Bus toModel(BusDto busDto) {
        Bus bus = new Bus();
        bus.setId(busDto.getId());
        bus.setLicense_plate(busDto.getLicensePlate());
        bus.setSeats(busDto.getSeats());
        // bus.setTravels(travelRepository.findAllById(busDto.getTravelsId()));
        return bus;
    }

    public List<BusDto> toBusDtoList(List<Bus> buses) {
        return buses.stream().map(b -> toDto(b)).collect(Collectors.toList());
    }

    public List<Bus> toBusList(List<BusDto> busesdto) {
        return busesdto.stream().map(b -> toModel(b)).collect(Collectors.toList());
    }
}
