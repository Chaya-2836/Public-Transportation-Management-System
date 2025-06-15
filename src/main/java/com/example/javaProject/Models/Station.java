package com.example.javaProject.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Station_Line> stationLines;
}
