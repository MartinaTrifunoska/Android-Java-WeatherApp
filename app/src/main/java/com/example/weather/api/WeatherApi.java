package com.example.weather.api;

import com.example.weather.models.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/weather?")
    Call<City> getWeatherData(@Query("q") String name, @Query("appid") String app_id, @Query("units") String metric);
}
