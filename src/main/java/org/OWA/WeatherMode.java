package org.OWA;

interface WeatherMode {
    void destroy();

    WeatherData getWeather(String city) throws Exception;
}
