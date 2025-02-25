package org.OWA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherSDK {
    private WeatherMode mode;

    public WeatherSDK(String apiKey, String mode) throws IllegalAccessException {
        if (mode.equalsIgnoreCase("on-demand")) {
            this.mode = new OnDemandMode(apiKey);
        } else if (mode.equalsIgnoreCase("polling")) {
            this.mode = new PollingMode(apiKey);
        } else {
            throw new IllegalAccessException("Invalid mode: " + mode);
        }
    }

    public String getWeather(String city) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(mode.getWeather(city));

    }

    public void destroy() {
        mode.destroy();
    }
}