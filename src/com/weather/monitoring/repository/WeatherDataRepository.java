package com.weather.monitoring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.weather.monitoring.model.DailyWeatherSummary;
import com.weather.monitoring.database.DatabaseConnection;

public class WeatherDataRepository {
    public void storeDailySummary(DailyWeatherSummary summary, String city) {
        String sql = "INSERT INTO daily_weather_summary (average_temperature, max_temperature, min_temperature, dominant_condition, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, (summary.getMaxTemperature() + summary.getMinTemperature()) / 2.0);  // Calculate average here
            pstmt.setDouble(2, summary.getMaxTemperature());
            pstmt.setDouble(3, summary.getMinTemperature());
            pstmt.setString(4, summary.getDominantCondition());
            pstmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));

            pstmt.executeUpdate();
            System.out.println("Weather data for " + city + " inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to insert weather data for " + city);
            e.printStackTrace();
        }
    }
}
