package com.gps.example.footprint.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Waypoints")
public class Waypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waypoint_id",columnDefinition = "INT")
    private Integer waypointId;

    @Column(name = "user_id", columnDefinition = "INT")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "latitude", precision = 10, scale = 8,columnDefinition = "DECIMAL(11, 8)")
    private Double latitude;

    @Column(name = "elevation", precision = 10, scale = 2, columnDefinition = "DECIMAL(10, 8)")
    private Double elevation;

    @Column(name = "longitude", precision = 11, scale = 8, columnDefinition = "DECIMAL(10, 2)")
    private Double longitude;

    @Column(name = "time")
    private LocalDateTime time;

    // Constructors
    public Waypoint() {
    }

    public Waypoint(Integer userId, String name, String comment, String description, Double latitude, Double elevation, Double longitude, LocalDateTime time) {
        this.userId = userId;
        this.name = name;
        this.comment = comment;
        this.description = description;
        this.latitude = latitude;
        this.elevation = elevation;
        this.longitude = longitude;
        this.time = time;
    }

    // Getters and Setters
    public Integer getWaypointId() {
        return waypointId;
    }

    public void setWaypointId(Integer waypointId) {
        this.waypointId = waypointId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "waypointId=" + waypointId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", elevation=" + elevation +
                ", longitude=" + longitude +
                ", time=" + time +
                '}';
    }
}
