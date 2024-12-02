package com.gps.example.footprint.service;

import com.gps.example.footprint.entity.Trackpoints;
import com.gps.example.footprint.repository.TrackPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackpointService {

    @Autowired
    private TrackPointRepository trackPointRepository;

    // Get all track points
    public List<Trackpoints> getAllTrackPoints() {
        return trackPointRepository.findAll();
    }

    // Get track point by ID
    public Optional<Trackpoints> getTrackPointById(Integer pointId) {
        return trackPointRepository.findById(pointId);
    }

    // Add new track point
    public Trackpoints addTrackPoint(Trackpoints trackPoint) {
        return trackPointRepository.save(trackPoint);
    }

    // Update an existing track point
    public Optional<Trackpoints> updateTrackPoint(Integer pointId, Trackpoints trackPointDetails) {
        return trackPointRepository.findById(pointId).map(trackPoint -> {
            trackPoint.setSegmentId(trackPointDetails.getSegmentId());
            trackPoint.setLatitude(trackPointDetails.getLatitude());
            trackPoint.setLongitude(trackPointDetails.getLongitude());
            return trackPointRepository.save(trackPoint);
        });
    }

    // Delete track point
    public boolean deleteTrackPoint(Integer pointId) {
        if (trackPointRepository.existsById(pointId)) {
            trackPointRepository.deleteById(pointId);
            return true;
        }
        return false;
    }
}

