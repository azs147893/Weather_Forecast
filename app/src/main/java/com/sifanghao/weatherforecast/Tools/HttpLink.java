package com.sifanghao.weatherforecast.Tools;

import java.net.URL;



import java.net.URL;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;



/**
 * Created by SiFanghao on 2015-10-10.
 */
public  class HttpLink {
    public static String getData(String url) {

        String line = "";
        StringBuilder result=new StringBuilder();
        try {
/*            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);*/
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            InputStream im=connection.getInputStream();
//            im= new GZIPInputStream(im);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(im));
            while ((line = rd.readLine()) != null) {
                result.append(line);
//            if(response.getStatusLine().getStatusCode()==200) {
//                HttpEntity entiry=response.getEntity();
//
//            }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(result.toString());
        return result.toString();
    }
}
