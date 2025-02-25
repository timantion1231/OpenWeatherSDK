package org.OWA;


public class PollingMode extends Weathers {
   // private final long TIMELIMIT = 10 * 60 * 1000;
   private final long TIMELIMIT = 5 * 1000;

    public PollingMode(String apiKey) {
        super(apiKey);
    }

    @Override
    public WeatherData getWeather(String city) {
        WeatherData data = super.getWeather(city);

        if (data.getLastUpdated()+ TIMELIMIT < System.currentTimeMillis()) {
            data = updateCache(city);
        }
        System.out.println("Updated: "+data.getLastUpdated());
        return data;
    }

    private WeatherData updateCache(String city) {
        WeatherData data = super.getCurrentWeather(city);
        super.cache.put(city, data);
        return data;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}