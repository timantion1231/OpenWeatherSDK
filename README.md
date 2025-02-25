# OpenWeatherSDK

This is an SDK for working with weather data using the OpenWeather API. With this SDK, you can get the current weather for various cities.
Data for no more than 10 cities can be stored simultaneously.
There are 2 modes of operation for the SDK: on demand and polling.
on demand mode: When working in this mode, calling the getWeather method will return a JSON with the current weather, and the data is updated with each request if more than 10 minutes have passed since the last update.
polling mode: When working in this mode, calling the getWeather method will return a JSON with the current weather, and the weather for all cities is automatically updated every 10 minutes.

## Installation

1. Download the OpenWeatherApi.jar file or build it yourself.
2. Add OpenWeatherApi.jar to your project's dependencies.

## Usage

Import the WeatherSDK class in your Java project:

Create an instance of WeatherSDK with your API key:

WeatherSDK sdk = new WSWeatherSDKDK("your_api_key", "mode");

Get the current weather for the specified city:

try {
System.out.println(weatherSDK.getWeather("your-city"));
} catch (Exception e) {
    System.err.println("Error fetching weather: " + e.getMessage());
}

## Methods

### WeatherSDK

- WeatherSDK(String apiKey, String mode) - Constructor to create an instance of the SDK with the specified API key and mode.

- Weather getWeather(String city) throws Exception - Get the current weather for the specified city.

- void destroy() - Destroy all data of the SDK object.

## Documentation

Detailed documentation is available in the Javadoc, which can be generated from the source code.
