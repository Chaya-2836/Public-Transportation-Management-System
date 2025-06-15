package com.example.javaProject.Dto;

import lombok.Data;

@Data
public class TravelDto {
    private long id;
    private Long busId;
    private Long driverId;
    private Long lineId;
    private String departureTime;
}
