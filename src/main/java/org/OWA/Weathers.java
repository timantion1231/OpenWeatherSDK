package org.OWA;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

abstract class Weathers implements WeatherMode {
    private String apiKey;
    protected Map<String, WeatherData> cache = new LinkedHashMap<>();
    private final long TIMELIMIT = 10 * 60 * 1000;
  // private final long TIMELIMIT = 5 * 1000;

    public Weathers(String apiKey) {
        this.apiKey = apiKey;
    }

    protected boolean isCacheValid(String city) {
        WeatherData data = cache.get(city);
        return data != null && data.getUpdatedTime() + TIMELIMIT > System.currentTimeMillis();
    }

    protected void addToCache(String city, WeatherData data) {
        if (cache.size() >= 10) {
            String oldestCity = cache.keySet().iterator().next();
            cache.remove(oldestCity);
        }
        cache.put(city, data);
    }

    protected WeatherData getWeatherAPI(String city) throws Exception {

        Map<String, Double> mapa = getCoordsByName(city);
        double lon = mapa.get("lon");
        double lat = mapa.get("lat");
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data/2.5/weather")
                .addQueryParameter("lat", String.valueOf(lat))
                .addQueryParameter("lon", String.valueOf(lon))
                .addQueryParameter("appid", apiKey)
                .addQueryParameter("units", "metric")
                .build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        WeatherData weatherData = new WeatherData();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                Gson gson = new Gson();
                Map<String, Object> data = gson.fromJson(response.body().string(), Map.class);
                Map<String, Object> main = (Map<String, Object>) data.get("main");
                double temperature = (Double) main.get("temp");
                double humidity = (Double) main.get("humidity");
                Map<String, Object> wind = (Map<String, Object>) data.get("wind");
                double windSpeed = (Double) wind.get("speed");
                List<Map<String, Object>> weatherList = (List<Map<String, Object>>) data.get("weather");
                String description = (String) weatherList.getFirst().get("description");

                weatherData = new WeatherData(city, temperature, humidity, windSpeed, description);
            }
        } catch (Exception e) {
            throw e;
        }
        return weatherData;
    }

    private Map<String, Double> getCoordsByName(String city) throws Exception {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("api.openweathermap.org")
                .addPathSegment("geo/1.0/direct")
                .addQueryParameter("q", city)
                .addQueryParameter("limit", "1")
                .addQueryParameter("appid", apiKey)
                .build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        HashMap<String, Double> map = new HashMap<>();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                Gson gson = new Gson();
                String responseStr = response.body().string();
                responseStr = responseStr.substring(1, responseStr.length() - 1);
                Location location = gson.fromJson(responseStr, Location.class);
                map.put("lon", location.getLon());
                map.put("lat", location.getLat());
            }
        } catch (IOException e) {
            throw e;
        }

        return map;
    }

    public void destroy() {
        cache.clear();
        this.apiKey = null;
    }

    public WeatherData getWeather(String city) throws Exception {
        if (isCacheValid(city)) {
            return cache.get(city);
        }
        WeatherData data = new WeatherData();
        try {
            data = getWeatherAPI(city);
            addToCache(city, data);
        } catch (Exception e) {
            throw e;
        }
        return data;
    }

    protected WeatherData getCurrentWeather(String city) throws Exception {
        WeatherData data = new WeatherData();
        try {
            data = getWeatherAPI(city);
        } catch (Exception e) {
            throw e;
        }
        return data;
    }
}
