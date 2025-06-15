package com.example.javaProject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javaProject.Dto.BusDto;
import com.example.javaProject.Services.BusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bus")
public class BusController {
    @Autowired
    public BusService busService;

    @GetMapping("/all")
    public ResponseEntity<List<BusDto>> getAll() {
        return ResponseEntity.ok().body(busService.getAllBuses());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody BusDto entity) {
        if (busService.createBus(entity))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().build();
    }

}
