package com.gps.example.footprint.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Track_points")
public class Trackpoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id", columnDefinition = "INT")
    private Integer pointId;

    @Column(name = "segment_id", columnDefinition = "INT")
    private Integer segmentId;

    @Column(name = "latitude", precision = 10, scale = 8,  columnDefinition = "DECIMAL(10, 8)")
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8, columnDefinition = "DECIMAL(11, 8)")
    private BigDecimal longitude;

    // Getters and Setters
    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}

