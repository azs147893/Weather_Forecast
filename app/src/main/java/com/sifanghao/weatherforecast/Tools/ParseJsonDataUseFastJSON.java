package com.sifanghao.weatherforecast.Tools;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sifanghao.weatherforecast.beans.BaiDuWeather;
import com.sifanghao.weatherforecast.beans.Result;
import com.sifanghao.weatherforecast.beans.TodayWeatherData;
import com.sifanghao.weatherforecast.beans.Weather_data_single;

/**
 * Created by SiFanghao on 2015-10-14.
 */
public class ParseJsonDataUseFastJSON implements ParseWeatherDataI{

    @Override
    public TodayWeatherData parse(String date) {

        TodayWeatherData todayWeatherData=new TodayWeatherData();
        BaiDuWeather baiduWather=null;
        try {
            baiduWather = JSON.parseObject(date, BaiDuWeather.class);
            Result today=baiduWather.getResults().get(0);
            Weather_data_single todayWeather=today.getWeather_data().get(0);
            todayWeatherData.setCity((baiduWather.getResults()).get(0).getCurrentCity());
            todayWeatherData.setType(todayWeather.getWeather());
            todayWeatherData.setPm25(today.getPm25());
            todayWeatherData.setFengxiang(todayWeather.getWind());
            todayWeatherData.setDate(todayWeather.getDate().substring(0, 2));
            todayWeatherData.setLow(todayWeather.getTemperature().substring(todayWeather.getTemperature().length() - 3, todayWeather.getTemperature().length()));
            todayWeatherData.setHigh(todayWeather.getTemperature().split(" ")[0]+
                    todayWeather.getTemperature().substring(todayWeather.getTemperature().length() - 1, todayWeather.getTemperature().length()));

        } catch (Exception e) {
            Log.e("ParseBaiDuJsonDataErroe",e.toString());
        }
        return todayWeatherData;
    }
}
