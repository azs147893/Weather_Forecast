package com.sifanghao.weatherforecast.Tools;

import com.sifanghao.weatherforecast.beans.TodayWeatherData;

/**
 * Created by SiFanghao on 2015-10-14.
 */
public interface ParseWeatherDataI {

    public TodayWeatherData parse(String date);
}
