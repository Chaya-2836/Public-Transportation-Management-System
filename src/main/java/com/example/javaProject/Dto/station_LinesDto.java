package com.example.javaProject.Dto;

import lombok.Data;

@Data
public class station_LinesDto {
    private long lineStationId;
    private Long stationId;
    private Long lineId;
    private int stationOrder;
}
