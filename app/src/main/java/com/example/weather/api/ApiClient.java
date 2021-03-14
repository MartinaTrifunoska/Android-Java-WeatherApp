package com.example.weather.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static WeatherApi weatherApi = null;

    public static WeatherApi getWeatherApiInstance() {
        if(weatherApi == null){
            weatherApi = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WeatherApi.class);
        }
        return weatherApi;
    }
}
