package com.example.javaProject.Convertors;

import java.util.List;
import java.util.stream.Collectors;

import com.example.javaProject.Dto.DriverDto;
import com.example.javaProject.Models.Driver;

public class DriverConvert {

    public DriverDto toDto(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setName(driver.getName());
        driverDto.setPhone(driver.getPhone());
        return driverDto;
    }

    public Driver toModel(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setId(driverDto.getId());
        driver.setName(driverDto.getName());
        driver.setPhone(driverDto.getPhone());
        return driver;
    }

    public List<DriverDto> toDriverDtoList(List<Driver> drivers) {
        return drivers.stream().map(d -> toDto(d)).collect(Collectors.toList());
    }

    public List<Driver> toDriverList(List<DriverDto> driversDto) {
        return driversDto.stream().map(d -> toModel(d)).collect(Collectors.toList());
    }
}
