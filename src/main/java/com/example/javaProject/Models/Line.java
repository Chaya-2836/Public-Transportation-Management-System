package com.example.javaProject.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String source;
    private String destination;

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Station_Line> stationLines;
}
