package com.sifanghao.weatherforecast.Tools;

import com.sifanghao.weatherforecast.beans.TodayWeatherData;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by SiFanghao on 2015-10-16.
 */
public class ParseXMLDataUseSAX extends DefaultHandler implements ParseWeatherDataI {

    private TodayWeatherData todayWeatherData=null;
    private String tagName=null;
    int elementNum=12;
    private int[] parsed=new int[elementNum];
    @Override
    public TodayWeatherData parse(String date) {
        try{
            SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new ByteArrayInputStream(date.getBytes()),this);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return todayWeatherData;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        todayWeatherData=new TodayWeatherData();
        for(int i=0;i<elementNum;i++)
            parsed[i]=0;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.tagName=localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String text=new String(ch,start,length);
        if(tagName.equals("city")&&parsed[0]==0){
            todayWeatherData.setCity(text);
            parsed[0]=1;
        }
        else if(tagName.equals("updatetime")&&parsed[1]==0){
            todayWeatherData.setUpdatetime(text);
            parsed[1]=1;
        }
        else if(tagName.equals("wendu")&&parsed[2]==0)
        {
            todayWeatherData.setWendu(text);
            parsed[2]=1;
        }
        else if(tagName.equals("fengli")&&parsed[3]==0)
        {
            todayWeatherData.setFengli(text);
            parsed[3]=1;
//                            Log.e("fengli",parser.getText());
        }
        else if(tagName.equals("shidu")&&parsed[4]==0)
        {

            todayWeatherData.setShidu(text);
            parsed[4]=1;
//                            Log.e("shidu",parser.getText());
        }
        else if(tagName.equals("fengxiang")&&parsed[5]==0)
        {

            todayWeatherData.setFengxiang(text);
            parsed[5]=1;
//                            Log.e("fengxiang",parser.getText());
        }
        else if(tagName.equals("pm25")&&parsed[6]==0)
        {

            todayWeatherData.setPm25(text);
            parsed[6]=1;
//                            Log.e("pm25",parser.getText());
        }
        else if(tagName.equals("quality")&&parsed[7]==0)
        {

            todayWeatherData.setQuality(text);
            parsed[7]=1;
//                            Log.e("quality",parser.getText());
        }
        else if(tagName.equals("date")&&parsed[8]==0)
        {

            todayWeatherData.setDate(text.substring(text.length() - 3, text.length()));
            parsed[8]=1;
//                            Log.e("date",parser.getText());
        }
        else if(tagName.equals("high")&&parsed[9]==0)
        {

            todayWeatherData.setHigh(text.substring(3));
            parsed[9]=1;
//                            Log.e("high",parser.getText());
        }
        else if(tagName.equals("low")&&parsed[10]==0)
        {

            todayWeatherData.setLow(text.substring(3));
            parsed[10]=1;
//                            Log.e("low",parser.getText());
        }
        else if(tagName.equals("type")&&parsed[11]==0)
        {

            todayWeatherData.setType(text);
            parsed[11]=1;
//                            Log.e("type",ChineseToEnglish.getPingYin(parser.getText()));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        tagName=null;
    }
}
