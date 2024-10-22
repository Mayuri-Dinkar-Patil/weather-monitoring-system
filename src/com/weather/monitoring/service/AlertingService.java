package com.weather.monitoring.service;

import com.weather.monitoring.model.WeatherData;

public class AlertingService {

    // Example threshold for temperature (in Celsius)
    private double temperatureThreshold;
    private int consecutiveUpdatesThreshold;
    private int consecutiveAlertCount;

    public AlertingService(double temperatureThreshold, int consecutiveUpdatesThreshold) {
        this.temperatureThreshold = temperatureThreshold;
        this.consecutiveUpdatesThreshold = consecutiveUpdatesThreshold;
        this.consecutiveAlertCount = 0;
    }

    // Method to check if the current weather exceeds the alert thresholds
    public void checkForAlert(WeatherData weatherData) {
        double currentTemp = weatherData.getMain().getTemp() - 273.15; // Convert from Kelvin to Celsius

        // Check if the temperature exceeds the threshold
        if (currentTemp > temperatureThreshold) {
            consecutiveAlertCount++;

            // If temperature exceeds threshold for consecutive updates, trigger alert
            if (consecutiveAlertCount >= consecutiveUpdatesThreshold) {
                triggerAlert(currentTemp, weatherData.getWeather().get(0).getDescription());
                consecutiveAlertCount = 0; // Reset count after alert
            }
        } else {
            consecutiveAlertCount = 0; // Reset count if threshold is not breached
        }
    }

    // Method to trigger an alert (could be console or email)
    private void triggerAlert(double temperature, String weatherCondition) {
        System.out.println("ALERT! Temperature has exceeded the threshold!");
        System.out.println("Current Temperature: " + temperature + "°C");
        System.out.println("Current Weather Condition: " + weatherCondition);
        // Here you can add an email alert system or other alerting mechanisms
    }
}
