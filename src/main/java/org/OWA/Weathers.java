package org.OWA;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Weathers implements WeatherMode{
    private String apiKey;
    protected Map<String, WeatherData> cache = new LinkedHashMap<>();

    public Weathers (String apiKey){
        this.apiKey = apiKey;
    }

    protected boolean isCacheValid(String city) {
        WeatherData data = cache.get(city);
        return data != null && (System.currentTimeMillis() - data.getLastUpdated() < 10 * 60 * 1000);
    }

    protected void updateCache(String city, WeatherData data) {
        if (cache.size() >= 10) {
            String oldestCity = cache.keySet().iterator().next();
            cache.remove(oldestCity);
        }
        cache.put(city, data);
    }

    protected WeatherData getWeatherAPI(String city) {
        return new WeatherData(city, 0, 0, 0, "");
    }

    public void destroy() {
        cache.clear();
        this.apiKey = null;
    }
    public String getWeather(String city)   {
        if (isCacheValid(city)) {
            return cache.get(city).getData();
        }
        WeatherData data = getWeatherAPI(city);
        updateCache(city, data);
        return data.getData();
    }
}
