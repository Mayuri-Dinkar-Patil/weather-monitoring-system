package com.weather.monitoring.service;
import com.weather.monitoring.model.WeatherData;

import com.weather.monitoring.service.WeatherService;

public class WeatherScheduler {
    private WeatherService weatherService;

    public WeatherScheduler() {
        this.weatherService = new WeatherService();
    }

    public void runScheduler(String city) {
        // Fetch and store weather data
        WeatherData weatherData = weatherService.getWeatherData(city);
        if (weatherData != null) {
            weatherService.storeWeatherData(weatherData);
        }

        // Calculate and store daily summary
        weatherService.calculateAndStoreDailySummary(city);
    }

    public static void main(String[] args) {
        WeatherScheduler scheduler = new WeatherScheduler();
        String city = "Delhi"; // Change to the city you want to monitor
        scheduler.runScheduler(city);
    }
}