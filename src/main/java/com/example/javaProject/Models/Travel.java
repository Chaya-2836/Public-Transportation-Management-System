package com.example.javaProject.Models;

import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "travels")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Bus_id")
    private Bus busId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Driver_id")
    private Driver driverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Line_id")
    private Line line;

    private LocalTime departureTime;
}
