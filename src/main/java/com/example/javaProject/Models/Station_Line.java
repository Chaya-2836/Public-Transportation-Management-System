package com.example.javaProject.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Station_Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Line_id_Station_id;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;

    private int stationOrder;
}
