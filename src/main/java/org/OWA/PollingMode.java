package org.OWA;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PollingMode extends Weathers {
    private ScheduledExecutorService scheduler;

    public PollingMode(String apiKey) {
        super(apiKey);
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(this::updateAllCache, 0, 10, TimeUnit.MINUTES);
    }

    @Override
    public String getWeather(String city) {
        return super.getWeather(city);
    }

    private void updateAllCache() {
        for (String city : super.cache.keySet()) {
            try {
                WeatherData data = super.getWeatherAPI(city);
                super.updateCache(city, data);
            } catch (Exception e) {
                // Обработка ошибок
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}