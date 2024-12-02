package com.gps.example.footprint.service;
import com.gps.example.footprint.entity.User;

import com.gps.example.footprint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
@Service
public class UserService {
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
    private UserRepository userRepository;

    // Fetch all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Fetch User by userId
    public Optional<User> getUserByUserId(Integer userId) {

        return userRepository.findById(userId);
    }

    // Save User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Update User
    public Optional<User> updateUser(Integer userId, User userDetails) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        });
    }

    // Delete User
    public boolean deleteUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
