package org.OWA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherSDK {

    /**
     * Class for working with the OpenWeather API
     * Can store the weather for only 10 cities
     */

    private WeatherMode mode;

    /**
     * Constructor
     * @param apiKey API key for working with the OpenWeather API
     * @param mode   Use "on-demand" to get the current weather when called.
     *               Use "polling" for automatic updates of the weather for all cities every 10 minutes
     */
    public WeatherSDK(String apiKey, String mode) throws IllegalAccessException {
        if (mode.equalsIgnoreCase("on-demand")) {
            this.mode = new OnDemandMode(apiKey);
        } else if (mode.equalsIgnoreCase("polling")) {
            this.mode = new PollingMode(apiKey);
        } else {
            throw new IllegalAccessException("Invalid mode: " + mode);
        }
    }

    public String getWeather(String city) throws Exception {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(mode.getWeather(city));

    }

    /**
     * Destroy object to cleaning memory
     */

    public void destroy() {
        mode.destroy();
    }
}