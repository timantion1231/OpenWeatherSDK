package org.OWA;

public class WeatherData {
    private String city;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private String description;
    private long lastUpdated;

    public WeatherData(String city, double temperature, double humidity, double windSpeed, String description) {

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
        return "";
    }
}