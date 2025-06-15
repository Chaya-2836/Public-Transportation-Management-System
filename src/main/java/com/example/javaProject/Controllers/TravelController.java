package com.example.javaProject.Controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javaProject.Dto.TravelDto;
import com.example.javaProject.Services.TravelService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    public TravelService travelService;

    @GetMapping("/all")
    public ResponseEntity<List<TravelDto>> getAll() {
        return ResponseEntity.ok().body(travelService.getAllTravels());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody TravelDto entity) {
        if (travelService.createTravel(entity))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getTravelByLineId/{number}")
    public ResponseEntity<List<TravelDto>> getTravelByLineId(@PathVariable String number) {
        return ResponseEntity.ok().body(travelService.getTravelByLineNumber(number));
    }

    @GetMapping("/getTravelByLineAndTime")
    public ResponseEntity<List<TravelService.LineTimeDto>> getTravelByLineAndTime(
            @RequestParam String line,
            @RequestParam LocalTime time) {
        return ResponseEntity.ok().body(travelService.getByLineAndTime(line, time));
    }

    @GetMapping("/lastTravel")
    public ResponseEntity<TravelDto> getLastTravel(@RequestParam String line) {
        TravelDto lastTravel = travelService.getLastTravelForLine(line);
        return lastTravel != null ? ResponseEntity.ok(lastTravel) : ResponseEntity.noContent().build();
    }

}
