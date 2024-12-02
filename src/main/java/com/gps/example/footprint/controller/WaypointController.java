package com.gps.example.footprint.controller;

import com.gps.example.footprint.entity.Waypoint;
import com.gps.example.footprint.repository.WaypointRepository;
import com.gps.example.footprint.service.WaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/waypoints")
public class WaypointController {

    @Autowired
    private WaypointService waypointService;
    private WaypointRepository waypointRepository;
    // Get all waypoints
    @GetMapping
    public ResponseEntity<List<Waypoint>> getAllWaypoints() {
        List<Waypoint> waypoints = waypointService.getAllWaypoints();
        if (waypoints.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(waypoints, HttpStatus.OK);
    }

    // Get waypoint by ID
    @GetMapping("/{waypointId}")
    public ResponseEntity<Waypoint> getWaypointById(@PathVariable Integer waypointId) {
        Optional<Waypoint> waypoint = waypointService.getWaypointById(waypointId);
        return waypoint.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get waypoints by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Waypoint>> getWaypointsByUserId(@PathVariable Integer userId) {
        List<Waypoint> waypoints = waypointService.getWaypointsByUserId(userId);
        if (waypoints.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(waypoints, HttpStatus.OK);
    }

    // Add new waypoint
    @PostMapping
    public ResponseEntity<Waypoint> addWaypoint(@RequestBody Waypoint waypoint) {
        try {
            Waypoint savedWaypoint = waypointService.addWaypoint(waypoint);
            return new ResponseEntity<>(savedWaypoint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update waypoint
    @PutMapping("/{waypointId}")
    public ResponseEntity<Waypoint> updateWaypoint(@PathVariable Integer waypointId, @RequestBody Waypoint waypointDetails) {
        Optional<Waypoint> updatedWaypoint = waypointService.updateWaypoint(waypointId, waypointDetails);
        return updatedWaypoint.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete waypoint
    @DeleteMapping("/{waypointId}")
    public ResponseEntity<HttpStatus> deleteWaypoint(@PathVariable Integer waypointId) {
        boolean isDeleted = waypointService.deleteWaypoint(waypointId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
