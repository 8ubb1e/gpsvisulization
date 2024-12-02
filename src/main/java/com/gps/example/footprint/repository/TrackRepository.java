package com.gps.example.footprint.repository;
import com.gps.example.footprint.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


public interface TrackRepository extends JpaRepository<Track, Integer> {
    List<Track> findByUserId(Integer userId);

    List<Track> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<Track> findByLatitudeAndLongitude(double latitude, double longitude);

    @Query("SELECT t FROM Track t WHERE "
            + "6371 * acos(cos(radians(:latitude)) * cos(radians(t.latitude)) * cos(radians(t.longitude) - radians(:longitude)) "
            + "+ sin(radians(:latitude)) * sin(radians(t.latitude))) <= :radius")
    List<Track> findByLocationWithinRadius(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius);
}
