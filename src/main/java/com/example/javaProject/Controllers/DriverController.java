package com.example.javaProject.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.javaProject.Dto.DriverDto;
import com.example.javaProject.Services.DriverService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    public DriverService driverService;

    @GetMapping("/all")
    public ResponseEntity<List<DriverDto>> getAll() {
        return ResponseEntity.ok().body(driverService.getAllDrivers());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody DriverDto entity) {
        if (driverService.createDriver(entity))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().build();
    }

}
