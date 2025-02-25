package org.OWA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherSDK {

    /**
     * Класс для работы с OpenWeather API
     * Может хранить погоду только 10 городов
     */

    private WeatherMode mode;

    /**
     * Конструктор
     * @param apiKey API ключ для работы с openWeather API
     * @param mode   Используйте "on-demand" для получения актуальной погоды при вызове
     *               Используйте "polling" для автоматическоо обновления погоды всех городов каждые 10 минут
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

    public void destroy() {
        mode.destroy();
    }
}