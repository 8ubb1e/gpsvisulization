package com.gps.example.footprint.controller;

import com.gps.example.footprint.entity.Trackpoints;
import com.gps.example.footprint.repository.TrackPointRepository;
import com.gps.example.footprint.service.TrackpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/track-points")
public class TrackpointsController {

    @Autowired
    private TrackpointService trackPointService;
    private TrackPointRepository trackPointRepository;

    // Get all track points
    @GetMapping
    public ResponseEntity<List<Trackpoints>> getAllTrackPoints() {
        List<Trackpoints> trackPoints = trackPointService.getAllTrackPoints();
        if (trackPoints.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trackPoints, HttpStatus.OK);
    }

    // Get track point by ID
    @GetMapping("/{pointId}")
    public ResponseEntity<Trackpoints> getTrackPointById(@PathVariable Integer pointId) {
        Optional<Trackpoints> trackPoint = trackPointService.getTrackPointById(pointId);
        return trackPoint.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add new track point
    @PostMapping
    public ResponseEntity<Trackpoints> addTrackPoint(@RequestBody Trackpoints trackPoint) {
        try {
            Trackpoints savedTrackPoint = trackPointService.addTrackPoint(trackPoint);
            return new ResponseEntity<>(savedTrackPoint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update track point
    @PutMapping("/{pointId}")
    public ResponseEntity<Trackpoints> updateTrackPoint(@PathVariable Integer pointId, @RequestBody Trackpoints trackPointDetails) {
        Optional<Trackpoints> updatedTrackPoint = trackPointService.updateTrackPoint(pointId, trackPointDetails);
        return updatedTrackPoint.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete track point
    @DeleteMapping("/{pointId}")
    public ResponseEntity<HttpStatus> deleteTrackPoint(@PathVariable Integer pointId) {
        boolean isDeleted = trackPointService.deleteTrackPoint(pointId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

