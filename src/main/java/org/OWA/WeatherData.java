package org.OWA;


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

    public WeatherData(String city, double temperature, double humidity, double windSpeed, String description) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
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

}