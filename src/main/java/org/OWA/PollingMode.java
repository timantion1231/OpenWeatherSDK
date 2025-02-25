package org.OWA;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PollingMode extends Weathers {

    ScheduledExecutorService executorService;

    public PollingMode(String apiKey) {
        super(apiKey);
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            try {
                updateAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.MINUTES);
    }


    @Override
    public WeatherData getWeather(String city) {
        return super.getWeather(city);
    }

    private void updateAll() {
        super.cache.forEach((key, value) -> {
            super.cache.put(key, super.getCurrentWeather(key));
        });
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}