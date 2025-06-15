package com.example.javaProject.Controllers;

import com.example.javaProject.Dto.station_LinesDto;
import com.example.javaProject.Services.StationLineService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station_line")
public class StationLineController {

    @Autowired
    private StationLineService stationLineService;

    @Data
    public static class AddStationToLineRequest {
        public String lineNumber;
        public String stationPhone;
        public Integer order;
    }

    @PostMapping("/add")
    public ResponseEntity<station_LinesDto> addStationToLine(@RequestBody AddStationToLineRequest request) {
        System.out.println("📥 קיבלנו בקשה להוספת תחנה:");
        System.out.println("lineNumber: " + request.lineNumber);
        System.out.println("stationPhone: " + request.stationPhone);
        System.out.println("order: " + request.order);

        try {
            station_LinesDto created = stationLineService.addStationToLine(
                    request.lineNumber, request.stationPhone, request.order);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Data
    public static class RemoveStationFromLineRequest {
        public String lineNumber;
        public String stationPhone;
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeStationFromLine(@RequestBody RemoveStationFromLineRequest request) {
        try {
            stationLineService.removeStationFromLine(request.lineNumber, request.stationPhone);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/line/{lineNumber}/info")
    public ResponseEntity<List<StationInfoDto>> getStationInfoByLine(@PathVariable String lineNumber) {
        List<StationInfoDto> infoList = stationLineService.getStationInfosByLine(lineNumber);
        if (infoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(infoList);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StationInfoDto {
        private String name;
        private String phone;
    }
}
