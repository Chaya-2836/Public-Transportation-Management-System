package com.example.javaProject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javaProject.Dto.StationDto;
import com.example.javaProject.Services.StationService;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    public StationService stationService;

    @GetMapping("/all")
    public ResponseEntity<List<StationDto>> getAll() {
        return ResponseEntity.ok().body(stationService.getAllStations());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody StationDto entity) {
        if (stationService.createStation(entity))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStation(@PathVariable Long id, @RequestBody StationDto stationDto) {
        boolean updated = stationService.updateStation(id, stationDto);
        if (updated) {
            return ResponseEntity.ok("Station updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
