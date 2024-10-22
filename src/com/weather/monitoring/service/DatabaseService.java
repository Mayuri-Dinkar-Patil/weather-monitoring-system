package com.weather.monitoring.service;

import com.weather.monitoring.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {

    public void saveWeatherData(String city, double temperature, String weatherCondition) {
        String query = "INSERT INTO weather_data (city, temperature, weather_condition) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, city);
            preparedStatement.setDouble(2, temperature);
            preparedStatement.setString(3, weatherCondition);

            preparedStatement.executeUpdate();
            System.out.println("Weather data saved successfully for city: " + city);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
