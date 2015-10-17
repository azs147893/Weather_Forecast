package com.sifanghao.weatherforecast.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.sifanghao.weatherforecast.beans.TodayWeatherData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by SiFanghao on 2015-10-11.
 */
public class ParseXMLData implements ParseWeatherDataI{

    @Override
    public  TodayWeatherData parse(String data){
        TodayWeatherData todayWeatherData=null;
        boolean isEnd=false;
        try{
            todayWeatherData=new TodayWeatherData();
            XmlPullParserFactory parserFactory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=parserFactory.newPullParser();
            parser.setInput(new StringReader(data));
            int eventType=parser.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("city"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setCity(parser.getText());
//                            Log.e("city",parser.getText());
                        }
                        else if(parser.getName().equals("updatetime"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setUpdatetime(parser.getText());
//                            Log.e("updatetime",parser.getText());
                        }
                        else if(parser.getName().equals("wendu"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setWendu(parser.getText());
//                            Log.e("wendu",parser.getText());
                        }
                        else if(parser.getName().equals("fengli"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setFengli(parser.getText());
//                            Log.e("fengli",parser.getText());
                        }
                        else if(parser.getName().equals("shidu"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setShidu(parser.getText());
//                            Log.e("shidu",parser.getText());
                        }
                        else if(parser.getName().equals("fengxiang"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setFengxiang(parser.getText());
//                            Log.e("fengxiang",parser.getText());
                        }
                        else if(parser.getName().equals("pm25"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setPm25(parser.getText());
//                            Log.e("pm25",parser.getText());
                        }
                        else if(parser.getName().equals("quality"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setQuality(parser.getText());
//                            Log.e("quality",parser.getText());
                        }
                        else if(parser.getName().equals("date"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setDate(parser.getText().substring(parser.getText().length()-3,parser.getText().length()));
//                            Log.e("date",parser.getText());
                        }
                        else if(parser.getName().equals("high"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setHigh(parser.getText().substring(3));
//                            Log.e("high",parser.getText());
                        }
                        else if(parser.getName().equals("low"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setLow(parser.getText().substring(3));
//                            Log.e("low",parser.getText());
                        }
                        else if(parser.getName().equals("type"))
                        {
                            eventType=parser.next();
                            todayWeatherData.setType(parser.getText());
//                            Log.e("type",ChineseToEnglish.getPingYin(parser.getText()));
                            isEnd=true;

                        }
                        break;
                }
                if(isEnd)
                    break;
                eventType=parser.next();
            }

        }
        catch (Exception e){

        }
        return todayWeatherData;
    }

}
