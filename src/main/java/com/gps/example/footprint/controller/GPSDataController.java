package com.gps.example.footprint.controller;

import com.gps.example.footprint.service.GPSDataService;
import com.gps.example.footprint.entity.HeatmapPoint;
import com.gps.example.footprint.entity.GPSData;
import com.gps.example.footprint.repository.GPSDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/gps-traces")
public class GPSDataController {

    @Autowired
    private GPSDataRepository gpsDataRepository;
    private GPSDataService gpsDataService;

    // Fetch all GPS data
    @GetMapping
    public ResponseEntity<List<GPSData>> getAllGPSData() {
        try {
            List<GPSData> gpsDataList = gpsDataRepository.findAll();
            if (gpsDataList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(gpsDataList, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Fetch GPS data for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GPSData>> getGPSDataByUserId(@PathVariable Long userId) {
        try {
            List<GPSData> userGPSData = gpsDataRepository.findByUserId(userId);
            if (userGPSData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
            return new ResponseEntity<>(userGPSData, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Add new GPS data
    @PostMapping
    public ResponseEntity<GPSData> addGPSData(@RequestBody GPSData gpsData) {
        try {
            GPSData savedData = gpsDataRepository.save(gpsData);
            return new ResponseEntity<>(savedData, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Update GPS data
    @PutMapping("/{id}")
    public ResponseEntity<GPSData> updateGPSData(@PathVariable Long id, @RequestBody GPSData gpsDataDetails) {
        try {
            Optional<GPSData> gpsData = gpsDataRepository.findById(id);
            if (gpsData.isPresent()) {
                GPSData _gpsData = gpsData.get();
                _gpsData.setLatitude(gpsDataDetails.getLatitude());
                _gpsData.setLongitude(gpsDataDetails.getLongitude());
                _gpsData.setTimestamp(gpsDataDetails.getTimestamp());
                return new ResponseEntity<>(gpsDataRepository.save(_gpsData), HttpStatus.OK); // 200 OK
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Delete GPS data
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGPSData(@PathVariable Long id) {
        try {
            gpsDataRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Fetch heatmap data
    @GetMapping("/heatmap")
    public ResponseEntity<List<HeatmapPoint>> getHeatmapData() {
        try {
            List<HeatmapPoint> heatmapPoints = gpsDataService.generateHeatmapPoints();
            if (heatmapPoints.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(heatmapPoints, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Filter GPS data by time range
    @GetMapping("/filter")
    public ResponseEntity<List<GPSData>> getGpsDataByTimeRange(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        try {
            List<GPSData> gpsDataList = gpsDataRepository.findByTimestampBetween(startTime, endTime);
            if (gpsDataList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(gpsDataList, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Bulk upload GPS data
    @PostMapping("/bulk")
    public ResponseEntity<List<GPSData>> addBulkGpsData(@RequestBody List<GPSData> gpsDataList) {
        try {
            List<GPSData> savedData = gpsDataRepository.saveAll(gpsDataList);
            return new ResponseEntity<>(savedData, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Fetch nearby GPS data within radius
    @GetMapping("/nearby")
    public ResponseEntity<List<GPSData>> getGpsDataByLocation(@RequestParam double lat, @RequestParam double lng, @RequestParam double radius) {
        try {
            List<GPSData> gpsDataList = gpsDataRepository.findByLocationWithinRadius(lat, lng, radius);
            if (gpsDataList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(gpsDataList, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

}
