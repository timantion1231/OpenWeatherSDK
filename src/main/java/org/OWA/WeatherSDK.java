package org.OWA;

public class WeatherSDK {
    private WeatherMode mode;

    public WeatherSDK(String apiKey, String mode) throws IllegalAccessException{
        if(mode.equalsIgnoreCase("on-demand")){
            this.mode = new PollingMode(apiKey);
        }
        else if(mode.equalsIgnoreCase("polling")){
            this.mode = new PollingMode(apiKey);
        }
        else{
            throw new IllegalAccessException("Invalid mode: "+mode);
        }
    }

    public String getWeather(String city) {
        return mode.getWeather(city);

    }

    public void destroy() {
        mode.destroy();
    }
}