package com.example.javaProject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.javaProject.Convertors.LineConverter;
import com.example.javaProject.Dto.LineDto;
import com.example.javaProject.Models.Line;
import com.example.javaProject.Repositories.LineRepository;

@Service
public class LineService {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineConverter lineConverter;

    public List<LineDto> getAllLinesDtos() {
        return lineConverter.toLineDtoList(lineRepository.findAll());
    }

    public boolean createLine(LineDto lineDto) {
        Line line = lineConverter.toModel(lineDto);
        lineRepository.save(line);
        return true;
    }

    public boolean deleteLine(Long id) {
        if (lineRepository.existsById(id)) {
            lineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
