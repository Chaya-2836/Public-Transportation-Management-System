package com.example.javaProject.Dto;

import lombok.Data;
import java.util.List;

@Data
public class LineDto {
    private long id;
    private String number;
    private String source;
    private String destination;

    // מזהים של תחנות שמחוברות לקו
    private List<Long> stationsIds;
}
