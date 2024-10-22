package com.weather.monitoring.model;

public class DailyWeatherSummary {
    private double averageTemperature;
    private double maxTemperature;
    private double minTemperature;
    private String dominantCondition;

    // Constructor with parameters
    public DailyWeatherSummary(double averageTemperature, double maxTemperature, double minTemperature, String dominantCondition) {
        this.averageTemperature = averageTemperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.dominantCondition = dominantCondition;
    }

    // Getters
    public double getAverageTemperature() {
        return averageTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public String getDominantCondition() {
        return dominantCondition;
    }
}
