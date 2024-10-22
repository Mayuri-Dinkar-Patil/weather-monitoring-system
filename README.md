# Weather Monitoring System

## Project Overview
The Weather Monitoring System is a real-time data processing application that retrieves weather data from the OpenWeatherMap API for several major cities in India (Delhi, Mumbai, Chennai, Bangalore, Kolkata, Hyderabad). It stores and processes weather information to provide daily weather summaries, including average, maximum, and minimum temperatures, as well as the dominant weather condition.

Additionally, the system can raise alerts when user-defined temperature thresholds are breached. The weather summaries and historical trends can be visualized for analysis.

## Dependencies
To run this project, the following software and libraries are required:

### Software:
- **Java**: Version 8 or above
- **MySQL**: Version 8.0
- **OpenWeatherMap API Key**: Sign up at [OpenWeatherMap](https://openweathermap.org) to obtain your API key.

### Libraries:
- **httpclient5-5.2.jar**: For HTTP requests.
- **gson-2.11.0.jar**: For JSON parsing.
- **mysql-connector-j-8.1.0.jar**: For MySQL database connection.
- **JUnit Jupiter (junit-jupiter-api-5.11.0.jar, junit-jupiter-engine-5.11.0.jar)**: For unit testing.
- **junit-platform-commons.jar, junit-platform-engine-1.1.jar, junit-platform-launcher-1.1.jar**: Additional JUnit dependencies for testing.
- **opentest4j-1.3.0.jar**: For assertions in tests.

## Setup Instructions
1. **Clone the repository**:
   ```bash
   git clone https://github.com/Mayuri-Dinkar-Patil/weather-monitoring-system.git
   cd weather-monitoring-system

