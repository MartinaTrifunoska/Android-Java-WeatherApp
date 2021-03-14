package com.example.weather.models;

public class Sys {
    private String country;
    private String sunset;
    private String sunrise;

    public Sys(String country, String sunset, String sunrise) {
        this.country = country;
        this.sunset = sunset;
        this.sunrise = sunrise;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
}
