package org.OWA;

import java.util.LinkedHashMap;
import java.util.Map;

public class OnDemandMode extends Weathers {


    public OnDemandMode(String apiKey) {
        super(apiKey);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public String getWeather(String city) {
        return super.getWeather(city);
    }
}
