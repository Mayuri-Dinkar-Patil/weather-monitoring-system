# weather-monitoring-system

## Project Overview
The Weather Monitoring System is a real-time data processing application that retrieves weather data from the OpenWeatherMap API for several major cities in India (Delhi, Mumbai, Chennai, Bangalore, Kolkata, Hyderabad). It stores and processes weather information to provide daily weather summaries, including average, maximum, and minimum temperatures, as well as the dominant weather condition.

Additionally, the system can raise alerts when user-defined temperature thresholds are breached. The weather summaries and historical trends can be visualized for analysis.

## Dependencies
To run this project, the following software and libraries are required:

### Software:
- Java 8 or above
- MySQL 8.0
- OpenWeatherMap API Key (sign up at https://openweathermap.org)
- Maven 3.x for building the project

### Libraries:
- `Gson` for JSON parsing
- `JDBC` for MySQL database connection
- `Hibernate` (optional) for ORM
- OpenWeatherMap API for weather data (`https://openweathermap.org/api`)

## Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/Mayuri-Dinkar-Patil/weather-monitoring-system.git
cd weather-monitoring-system
