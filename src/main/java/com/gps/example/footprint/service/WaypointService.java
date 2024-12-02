package com.gps.example.footprint.service;

import com.gps.example.footprint.entity.Waypoint;
import com.gps.example.footprint.repository.WaypointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaypointService {

    @Autowired
    private WaypointRepository waypointRepository;

    // Get all waypoints
    public List<Waypoint> getAllWaypoints() {
        return waypointRepository.findAll();
    }

    // Get waypoint by ID
    public Optional<Waypoint> getWaypointById(Integer waypointId) {
        return waypointRepository.findById(waypointId);
    }

    // Get waypoints by user ID
    public List<Waypoint> getWaypointsByUserId(Integer userId) {
        return waypointRepository.findByUserId(userId);
    }

    // Add new waypoint
    public Waypoint addWaypoint(Waypoint waypoint) {
        return waypointRepository.save(waypoint);
    }

    // Update an existing waypoint
    public Optional<Waypoint> updateWaypoint(Integer waypointId, Waypoint waypointDetails) {
        return waypointRepository.findById(waypointId).map(waypoint -> {
            waypoint.setUserId(waypointDetails.getUserId());
            waypoint.setName(waypointDetails.getName());
            waypoint.setComment(waypointDetails.getComment());
            waypoint.setDescription(waypointDetails.getDescription());
            waypoint.setLatitude(waypointDetails.getLatitude());
            waypoint.setElevation(waypointDetails.getElevation());
            waypoint.setLongitude(waypointDetails.getLongitude());
            waypoint.setTime(waypointDetails.getTime());
            return waypointRepository.save(waypoint);
        });
    }

    // Delete waypoint
    public boolean deleteWaypoint(Integer waypointId) {
        if (waypointRepository.existsById(waypointId)) {
            waypointRepository.deleteById(waypointId);
            return true;
        }
        return false;
    }
}
