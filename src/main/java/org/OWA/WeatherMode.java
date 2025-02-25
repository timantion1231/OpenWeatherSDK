package org.OWA;

public interface WeatherMode {
    void destroy();
    WeatherData getWeather(String city);
}
