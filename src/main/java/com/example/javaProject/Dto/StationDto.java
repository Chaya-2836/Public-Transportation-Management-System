package com.example.javaProject.Dto;

import lombok.Data;
import java.util.List;

@Data
public class StationDto {
    private Long id;
    private String name;
    private String phone;

    // מזהים של קווים שמחוברים לתחנה
    private List<Long> linesIds;
}
