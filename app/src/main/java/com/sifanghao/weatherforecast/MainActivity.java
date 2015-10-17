package com.sifanghao.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sifanghao.weatherforecast.Tools.ChineseToEnglish;
import com.sifanghao.weatherforecast.Tools.HttpLink;
import com.sifanghao.weatherforecast.Tools.NetUtil;
import com.sifanghao.weatherforecast.Tools.ParseXMLData;
import com.sifanghao.weatherforecast.beans.TodayWeatherData;
import com.sifanghao.weatherforecast.factory.ParseWeatherDataFactory;

public class MainActivity extends AppCompatActivity {

    private TextView
            wind ,
            city_name,
            time_block_city_name,
            time_block_time,
            time_block_shidu,
            pm25,
            pollutionLevel,
            week,
            temperature,
            weatherType;

    private ImageView share,
            pollutionLevel_img,
            weatherType_img;

    private Context context=this;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                initView();

                handler=new Handler() {
                    @Override
                    public void handleMessage(Message msg){
                            switch(msg.what){
                                case 0:
//                        SharedPreferences netDate=(SharedPreferences)getSharedPreferences("shared",MODE_PRIVATE);
//                        Toast.makeText(context,"heihei",Toast.LENGTH_SHORT).show();
                                    updateWeatherDate((TodayWeatherData)msg.obj);
                                    break;
                        }

            }
        };



        share=(ImageView)findViewById(R.id.title_update_img);
        share.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if(NetUtil.getNetWorkState(v.getContext())!=NetUtil.NETWORKSTATE_NONE) {
                                            new Thread(){
                                                @Override
                                                public void run() {
                                                    String result;

//                                                    result=(HttpLink.getData("http://wthrcdn.etouch.cn/WeatherApi?citykey=101010100"));
                                                    result=(HttpLink.getData("http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=4MBWabuBqXiv1ObsKABSGW8p"));
                                                    Message msg=new Message();
                                                    msg.what=0;
                                                    msg.obj= ParseWeatherDataFactory.getParser(ParseWeatherDataFactory.ParserType_JSON).parse(result);
                                                    handler.sendMessage(msg);

//                                                SharedPreferences netDate=(SharedPreferences)getSharedPreferences("shared",MODE_PRIVATE);
//                                                SharedPreferences.Editor editor=netDate.edit();
//                                                editor.putString("netData",result);
//                                                editor.commit();
                                                }
                                            }.start();
                                        }
                                        else{
                                            Toast.makeText(context,"无网络连接，无法更新",Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        wind=(TextView)findViewById(R.id.wind);
        city_name=(TextView)findViewById(R.id.city_name);
        time_block_city_name=(TextView)findViewById(R.id.time_block_city_name);
        time_block_time=(TextView)findViewById(R.id.time_block_time);
        time_block_shidu=(TextView)findViewById(R.id.time_block_shidu);
        pm25=(TextView)findViewById(R.id.pm25);
        pollutionLevel=(TextView)findViewById(R.id.pollutionLevel);
        week=(TextView)findViewById(R.id.week);
        temperature=(TextView)findViewById(R.id.temperature);
        weatherType=(TextView)findViewById(R.id.weatherType);

        pollutionLevel_img=(ImageView)findViewById(R.id.pollutionLevel_img);
        weatherType_img=(ImageView)findViewById(R.id.weatherType_img);

        wind.setText("N/A");
        city_name.setText("N/A");
        time_block_city_name.setText("N/A");
        time_block_time.setText("N/A");
        time_block_shidu.setText("N/A");
        pm25.setText("N/A");
        pollutionLevel.setText("N/A");
        week.setText("N/A");
        temperature.setText("N/A");
        weatherType.setText("N/A");

        pollutionLevel_img.setImageResource(getResources().getIdentifier("biz_plugin_weather_0_50", "drawable", getApplicationInfo().packageName));

//        Log.e("tupianName", getResources().getResourceName(R.drawable.biz_plugin_weather_baoxue));
//        Log.e("tupianName", ""+getResources().getIdentifier("biz_plugin_weather_151_200", "drawable", getApplicationInfo().packageName));

    }
    private void updateWeatherDate(TodayWeatherData todayWeatherData){
        int i_pm25=new Integer(todayWeatherData.getPm25());
        String s_weatherType="biz_plugin_weather_"+ ChineseToEnglish.getPingYin(todayWeatherData.getType()).trim();
//        String s_weatherType="biz_plugin_weather_"+ "baoyu";
        wind.setText(todayWeatherData.getFengxiang());
        city_name.setText(todayWeatherData.getCity()+"天气");
        time_block_city_name.setText(todayWeatherData.getCity());
        time_block_time.setText("今天"+todayWeatherData.getUpdatetime()+"发布");
        time_block_shidu.setText("湿度"+todayWeatherData.getShidu());
        pm25.setText(todayWeatherData.getPm25());
        pollutionLevel.setText(todayWeatherData.getQuality());
        week.setText("今天"+todayWeatherData.getDate());
        temperature.setText(todayWeatherData.getLow()+"~"+todayWeatherData.getHigh());
        weatherType.setText(todayWeatherData.getType());

        //设置污染等级图标
        if(i_pm25<=50)
            pollutionLevel_img.setImageResource(R.drawable.biz_plugin_weather_0_50);
        else  if(i_pm25<=100)
            pollutionLevel_img.setImageResource(R.drawable.biz_plugin_weather_51_100);
        else  if(i_pm25<=150)
            pollutionLevel_img.setImageResource(R.drawable.biz_plugin_weather_101_150);
        else  if(i_pm25<=200)
            pollutionLevel_img.setImageResource(R.drawable.biz_plugin_weather_151_200);
        else  if(i_pm25<=250)
            pollutionLevel_img.setImageResource(R.drawable.biz_plugin_weather_201_300);
        else
            pollutionLevel_img.setImageResource(R.drawable.biz_plugin_weather_greater_300);

//        Log.e("tupianName", s_weatherType);
//        weatherType_img.setImageResource(getResources().getIdentifier(s_weatherType, "drawable", getApplicationInfo().packageName));
    }
}