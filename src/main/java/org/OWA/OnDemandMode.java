package org.OWA;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OnDemandMode extends Weathers {


    public OnDemandMode(String apiKey) {
        super(apiKey);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public WeatherData getWeather(String city) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return super.getWeather(city);
    }
}
