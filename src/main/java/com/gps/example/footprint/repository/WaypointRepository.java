package com.gps.example.footprint.repository;
import com.gps.example.footprint.entity.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.List;

public interface WaypointRepository extends JpaRepository<Waypoint, Integer> {
    List<Waypoint> findByUserId(Integer userId);
    Optional<Waypoint> findByWaypointId(Integer waypointId);
    List<Waypoint> findByLatitudeAndLongitude(double latitude, double longitude);
    }



