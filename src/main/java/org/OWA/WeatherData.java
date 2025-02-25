package org.OWA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class WeatherData {
    @Expose
    private String city;
    @Expose
    private double temperature;
    @Expose
    private double humidity;
    @Expose
    private double windSpeed;
    @Expose
    private String description;
    private long lastUpdated;

    public WeatherData(String city, double temperature, double humidity, double windSpeed, String description) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
        this.lastUpdated = System.currentTimeMillis();
    }

    public WeatherData() {
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public String getData() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }

    public Map<String, Object> getData1(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("city", this.city);
        data.put("temperature", this.temperature);
        data.put("humidity", this.humidity);
        data.put("windSpeed", this.windSpeed);
        data.put("description", this.description);
        data.put("lastUpdated", this.lastUpdated);
        return data;
    }

    public void setLastUpdated() {
        lastUpdated = System.currentTimeMillis();
    }

}