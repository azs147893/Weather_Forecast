package com.sifanghao.weatherforecast.beans;

import java.util.ArrayList;

/**
 * Created by SiFanghao on 2015-10-16.
 */
public class Result {
    private  String currentCity;
    private String pm25;
    private ArrayList<Weather_data_single> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public ArrayList<Weather_data_single> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(ArrayList<Weather_data_single> weather_data) {
        this.weather_data = weather_data;
    }
}
