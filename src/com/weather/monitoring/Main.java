package com.weather.monitoring;

import com.weather.monitoring.model.WeatherData;
import com.weather.monitoring.service.WeatherService;

public class Main {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();
        WeatherData weatherData = weatherService.getWeatherData("Mumbai");

        if (weatherData != null && weatherData.getMain() != null) {
            System.out.println("City: " + weatherData.getName()); // Should work now
            System.out.println("Temperature: " + weatherData.getMain().getTemp());
            System.out.println("Weather: " + weatherData.getWeather().get(0).getDescription());
        } else {
            System.out.println("Failed to retrieve weather data.");
        }
    }
}
