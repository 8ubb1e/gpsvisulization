package com.gps.example.footprint.entity;

public class HeatmapPoint {
    private double latitude;
    private double longitude;
    private int intensity; // 热度或权重

    // 构造函数
    public HeatmapPoint(double latitude, double longitude, int intensity) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.intensity = intensity;
    }

    // Getter 和 Setter 方法
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
