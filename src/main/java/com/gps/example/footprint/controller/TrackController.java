package com.gps.example.footprint.controller;

import com.gps.example.footprint.entity.Track;
import com.gps.example.footprint.repository.TrackRepository;
import com.gps.example.footprint.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;
    private TrackService trackService;
    // Fetch all tracks
    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks() {
        try {
            List<Track> trackList = trackRepository.findAll();
            if (trackList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(trackList, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Fetch track by track ID
    @GetMapping("/{trackId}")
    public ResponseEntity<Track> getTrackById(@PathVariable("trackId") Integer trackId) {
        try {
            Optional<Track> track = trackRepository.findById(trackId);
            if (track.isPresent()) {
                return new ResponseEntity<>(track.get(), HttpStatus.OK); // 200 OK
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Add new track
    @PostMapping
    public ResponseEntity<Track> addTrack(@RequestBody Track track) {
        try {
            Track savedTrack = trackRepository.save(track);
            return new ResponseEntity<>(savedTrack, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    // Fetch tracks by location within radius
    @GetMapping("/location")
    public ResponseEntity<List<Track>> getTracksByLocation(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius) {
        try {
            List<Track> trackList = trackRepository.findByLocationWithinRadius(latitude, longitude, radius);
            if (trackList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(trackList, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Update track by track ID
    @PutMapping("/{trackId}")
    public ResponseEntity<Track> updateTrack(@PathVariable("trackId") Integer trackId, @RequestBody Track trackDetails) {
        try {
            Optional<Track> existingTrack = trackRepository.findById(trackId);
            if (existingTrack.isPresent()) {
                Track updatedTrack = existingTrack.get();
                updatedTrack.setUserId(trackDetails.getUserId());
                updatedTrack.setName(trackDetails.getName());
                updatedTrack.setStartTime(trackDetails.getStartTime());
                updatedTrack.setEndTime(trackDetails.getEndTime());
                updatedTrack.setDescription(trackDetails.getDescription());
                updatedTrack.setLongitude(trackDetails.getLongitude());
                updatedTrack.setLatitude(trackDetails.getLatitude());
                updatedTrack.setLength(trackDetails.getLength());
                return new ResponseEntity<>(trackRepository.save(updatedTrack), HttpStatus.OK); // 200 OK
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    // Delete track by track ID
    @DeleteMapping("/{trackId}")
    public ResponseEntity<HttpStatus> deleteTrack(@PathVariable("trackId") Integer trackId) {
        try {
            if (trackRepository.existsById(trackId)) {
                trackRepository.deleteById(trackId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}
