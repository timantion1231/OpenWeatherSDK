package org.OWA;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class PollingMode extends Weathers {

    ScheduledExecutorService executorService;

    public PollingMode(String apiKey) {
        super(apiKey);
        executorService = Executors.newScheduledThreadPool(1);
        try {
            executorService.scheduleAtFixedRate(() -> {
                try {
                    updateAll();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, 0, 10, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public WeatherData getWeather(String city) throws Exception {
        return super.getWeather(city);
    }

    private void updateAll() throws Exception {
        super.cache.forEach((key, value) -> {
            try {
                super.cache.put(key, super.getCurrentWeather(key));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}