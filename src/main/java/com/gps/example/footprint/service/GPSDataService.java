package com.gps.example.footprint.service;

import com.gps.example.footprint.entity.HeatmapPoint;
import com.gps.example.footprint.entity.GPSData;
import com.gps.example.footprint.repository.GPSDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class GPSDataService {

    private final String instanceConnectionName = "gps-heatmap-mysql84";
    private final String databaseName = "gpx-sql";
    private final String username = "root";
    private final String password = "kJVaC)=gXn1~.J6o";

    public void connectToDatabase() {
        String jdbcUrl = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=%s&password=%s",
                databaseName, instanceConnectionName, username, password
        );

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table");
            while (resultSet.next()) {
                // 处理每一行数据
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    @Autowired
    private GPSDataRepository gpsDataRepository;

    public List<HeatmapPoint> generateHeatmapPoints() {
        List<GPSData> gpsDataList = gpsDataRepository.findAll();
        List<HeatmapPoint> heatmapPoints = new ArrayList<>();

        // 将 GPS 数据转换为 HeatmapPoint
        for (GPSData data : gpsDataList) {
            //
            HeatmapPoint point = new HeatmapPoint(data.getLatitude(), data.getLongitude(), 1);
            heatmapPoints.add(point);
        }

        return heatmapPoints;
    }



}


