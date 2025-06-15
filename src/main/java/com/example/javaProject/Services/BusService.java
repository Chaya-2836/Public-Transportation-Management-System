package com.example.javaProject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.javaProject.Convertors.BusConvert;
import com.example.javaProject.Dto.BusDto;
import com.example.javaProject.Models.Bus;
import com.example.javaProject.Repositories.BusRepository;

@Service
public class BusService {
    @Autowired
    public BusRepository busRepository;
    @Autowired
    public BusConvert busConvert;

    public List<BusDto> getAllBuses() {
        return busConvert.toBusDtoList(busRepository.findAll());
    }

    public boolean createBus(BusDto busDto) {
        Bus bus = busConvert.toModel(busDto);
        if (!busRepository.exists(Example.of(bus))) {
            busRepository.save(bus);
            return true;
        }
        return false;
    }

    public boolean deleteBus(Long id) {
        if (busRepository.existsById(id)) {
            busRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
