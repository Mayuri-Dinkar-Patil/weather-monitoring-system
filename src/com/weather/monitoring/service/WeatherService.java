package com.weather.monitoring.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weather.monitoring.model.WeatherData;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class WeatherService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/weatherdb";
    private static final String USER = "root"; // Update with your DB username
    private static final String PASS = "Mayuri@1512"; // Update with your DB password
    private static final String API_KEY = "682104dd2d6a201f7242d17967971a9c"; // Replace with your API key
    private static final Gson gson = new Gson(); // Create a Gson instance

    public WeatherData getWeatherData(String city) {
        WeatherData weatherData = null;
        try {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Check for a successful response
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("Error: Unable to fetch weather data for city: " + city);
                return null;
            }

            // Read response
            StringBuilder inline = new StringBuilder();
            try (Scanner sc = new Scanner(url.openStream())) {
                while (sc.hasNext()) {
                    inline.append(sc.nextLine());
                }
            }

            // Parse the response using Gson
            JsonObject jsonResponse = gson.fromJson(inline.toString(), JsonObject.class);
            weatherData = new WeatherData();
            weatherData.setName(jsonResponse.get("name").getAsString());

            // Extract main temperature data
            WeatherData.Main main = new WeatherData.Main();
            main.setTemp(jsonResponse.getAsJsonObject("main").get("temp").getAsDouble());
            weatherData.setMain(main);

            // Extract weather conditions
            JsonArray weatherArray = jsonResponse.getAsJsonArray("weather");
            WeatherData.Weather weatherCondition = new WeatherData.Weather();
            weatherCondition.setDescription(weatherArray.get(0).getAsJsonObject().get("description").getAsString());
            weatherData.setWeather(List.of(weatherCondition)); // Populate weather list

        } catch (Exception e) {
            System.err.println("Error occurred while fetching weather data: " + e.getMessage());
            e.printStackTrace();
        }
        return weatherData;
    }

    public void storeWeatherData(WeatherData weatherData) {
        String query = "INSERT INTO weather_data (city, temperature, weather_condition, timestamp) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, weatherData.getName());
            pstmt.setDouble(2, weatherData.getMain().getTemp());
            pstmt.setString(3, weatherData.getWeather().get(0).getDescription());
            pstmt.executeUpdate();
            System.out.println("Weather data stored successfully for city: " + weatherData.getName());
        } catch (SQLException e) {
            System.err.println("SQL error while storing weather data for city " + weatherData.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void calculateAndStoreDailySummary(String city) {
        String query = "SELECT AVG(temperature) AS avg_temp, MAX(temperature) AS max_temp, MIN(temperature) AS min_temp, weather_condition " +
                       "FROM weather_data WHERE city = ? AND DATE(timestamp) = CURDATE() GROUP BY weather_condition";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, city);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                double avgTemp = rs.getDouble("avg_temp");
                double maxTemp = rs.getDouble("max_temp");
                double minTemp = rs.getDouble("min_temp");
                String weatherCondition = rs.getString("weather_condition");

                // Check if weatherCondition is null
                if (weatherCondition == null) {
                    System.out.println("Weather condition for city " + city + " is null. Skipping...");
                    continue; // Skip if weatherCondition is null
                }

                // Insert daily summary into the database
                String insertQuery = "INSERT INTO daily_weather_summary (city, average_temperature, max_temperature, min_temperature, dominant_condition, created_at) VALUES (?, ?, ?, ?, ?, NOW())";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, city);
                    insertStmt.setDouble(2, avgTemp);
                    insertStmt.setDouble(3, maxTemp);
                    insertStmt.setDouble(4, minTemp);
                    insertStmt.setString(5, weatherCondition);
                    insertStmt.executeUpdate();
                }
            }
            System.out.println("Daily summary calculated and stored for city: " + city);
        } catch (SQLException e) {
            System.err.println("SQL error while calculating daily summary for city " + city + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
