package org.OWA;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class OnDemandMode extends Weathers {


    public OnDemandMode(String apiKey) {
        super(apiKey);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public WeatherData getWeather(String city) throws Exception {
        return super.getWeather(city);
    }
}
