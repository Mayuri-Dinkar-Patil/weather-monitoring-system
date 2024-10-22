package com.weather.monitoring.model;

import java.util.List;

public class WeatherData {
    private String name;
    private Main main;
    private List<Weather> weather;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }

    public static class Main {
        private double temp;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }
    }

    public static class Weather {
        private String description;

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
