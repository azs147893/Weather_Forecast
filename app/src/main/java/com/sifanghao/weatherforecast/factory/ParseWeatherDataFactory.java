package com.sifanghao.weatherforecast.factory;

import com.sifanghao.weatherforecast.Tools.ParseJsonDataUseFastJSON;
import com.sifanghao.weatherforecast.Tools.ParseWeatherDataI;
import com.sifanghao.weatherforecast.Tools.ParseXMLData;
import com.sifanghao.weatherforecast.Tools.ParseXMLDataUseSAX;

/**
 * Created by SiFanghao on 2015-10-14.
 */
public class ParseWeatherDataFactory {



    public static final int ParserType_XML_PULL=1;
    public static final int ParserType_XML_SAX=2;
    public static final int ParserType_JSON=3;

    public static ParseWeatherDataI getParser(int type){
        ParseWeatherDataI result=null;
        switch (type){
            case ParserType_XML_PULL:
                result= new ParseXMLData();
                break;
            case ParserType_XML_SAX:
                result= new ParseXMLDataUseSAX();
                break;
            case ParserType_JSON:
                result= new ParseJsonDataUseFastJSON();
                break;
        }
        return result;
    }
}
