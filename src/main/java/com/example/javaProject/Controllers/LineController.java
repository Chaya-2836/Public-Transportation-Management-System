package com.example.javaProject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javaProject.Dto.LineDto;
import com.example.javaProject.Services.LineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/line")
public class LineController {

    @Autowired
    public LineService lineService;

    @GetMapping("/all")
    public ResponseEntity<List<LineDto>> getAll() {
        return ResponseEntity.ok().body(lineService.getAllLinesDtos());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody LineDto entity) {
        if (lineService.createLine(entity))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().build();
    }

}
