package org.OWA;

import java.util.LinkedHashMap;
import java.util.Map;

public class OnDemandMode implements WeatherMode {
    private String apiKey;
    private Map<String, WeatherData> cache = new LinkedHashMap<>();

    public OnDemandMode(String apiKey) {

    }

    @Override
    public String getWeather(String city) {
        return "";
    }

    private boolean isCacheValid(String city) {
        return true;
    }

    private void updateCache(String city, WeatherData data) {

    }

    private WeatherData getWeatherAPI(String city) {
        return new WeatherData(city, 0, 0, 0, "");
    }

    @Override
    public void destroy(){

    }

}
