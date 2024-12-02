package com.gps.example.footprint.repository;

import com.gps.example.footprint.entity.Trackpoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackPointRepository extends JpaRepository<Trackpoints,Integer> {
    List<Trackpoints> findBypointId(Integer pointId);
}
