package com.gps.example.footprint.repository;
import com.gps.example.footprint.entity.GPSData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GPSDataRepository extends JpaRepository<GPSData, Long> {

    List<GPSData> findByUserId(Long userId);

    List<GPSData> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<GPSData> findByLatitudeAndLongitude(double latitude, double longitude);

    // 自定义查询：基于指定的经纬度和半径查找数据
    @Query("SELECT g FROM GPSData g WHERE "
            + "6371 * acos(cos(radians(:latitude)) * cos(radians(g.latitude)) * cos(radians(g.longitude) - radians(:longitude)) "
            + "+ sin(radians(:latitude)) * sin(radians(g.latitude))) <= :radius")
    List<GPSData> findByLocationWithinRadius(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius);
}





