package com.gps.example.footprint.service;
import com.gps.example.footprint.repository.TrackRepository;
import com.gps.example.footprint.entity.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    // Fetch all Tracks
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    // Fetch Track by ID
    public Optional<Track> getTrackById(Integer trackId) {
        return trackRepository.findById(trackId);
    }

    // Save Track
    public Track saveTrack(Track trackId) {
        return trackRepository.save(trackId);
    }

    // Update Track
    public Optional<Track> updateTrack(Integer trackID, Track trackDetails) {
        return trackRepository.findById(trackID).map(track -> {
            track.setName(trackDetails.getName());
            track.setStartTime(trackDetails.getStartTime());
            track.setEndTime(trackDetails.getEndTime());
            track.setDescription(trackDetails.getDescription());
            track.setLongitude(trackDetails.getLongitude());
            track.setLatitude(trackDetails.getLatitude());
            track.setLength(trackDetails.getLength());
            return trackRepository.save(track);
        });
    }

    // Delete Track
    public boolean deleteTrack(Integer trackID) {
        if (trackRepository.existsById(trackID)) {
            trackRepository.deleteById(trackID);
            return true;
        }
        return false;
    }
}

