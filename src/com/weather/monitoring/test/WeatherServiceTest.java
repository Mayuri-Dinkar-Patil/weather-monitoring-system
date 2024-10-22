package com.weather.monitoring.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.weather.monitoring.service.WeatherService;
import com.weather.monitoring.model.WeatherData;

public class WeatherServiceTest {

    @Test
    public void testGetWeatherData() {
        WeatherService weatherService = new WeatherService();
        String city = "Mumbai";
        WeatherData weatherData = weatherService.getWeatherData(city);

        assertNotNull(weatherData);
        assertEquals(city, weatherData.getName()); // Use getName() instead of getCity()
    }
}


