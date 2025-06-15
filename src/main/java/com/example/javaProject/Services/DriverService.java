package com.example.javaProject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.javaProject.Convertors.DriverConvert;
import com.example.javaProject.Dto.DriverDto;
import com.example.javaProject.Models.Driver;
import com.example.javaProject.Repositories.DriverRepository;

@Service
public class DriverService {
    @Autowired
    public DriverRepository driverRepository;

    @Autowired
    public DriverConvert driverConvert;

    public List<DriverDto> getAllDrivers() {
        return driverConvert.toDriverDtoList(driverRepository.findAll());
    }

    public boolean createDriver(DriverDto driverDto) {
        Driver driver = driverConvert.toModel(driverDto);
        if (!driverRepository.exists(Example.of(driver))) {
            driverRepository.save(driver);
            return true;
        }
        return false;
    }

    public boolean deleteDriver(Long id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
