package com.sifanghao.weatherforecast.Tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by SiFanghao on 2015-10-14.
 */
public class NetUtil {
    public static final int NETWORKSTATE_NONE=0;
    public static final int NETWORKSTATE_WIFI=1;
    public static final int NETWORKSTATE_MOBILE=2;

    public static int getNetWorkState(Context context)
    {
        int netState;
        ConnectivityManager connManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state=connManager.getNetworkInfo(connManager.TYPE_WIFI).getState();
        if(state== NetworkInfo.State.CONNECTED||state==NetworkInfo.State.CONNECTING){
            netState=NETWORKSTATE_WIFI;
        }
        else{
            state=connManager.getNetworkInfo(connManager.TYPE_MOBILE).getState();
            if(state== NetworkInfo.State.CONNECTED||state==NetworkInfo.State.CONNECTING){
                netState=NETWORKSTATE_MOBILE;
            }
            else
                netState=NETWORKSTATE_NONE;
        }


//        NetworkInfo networkInfo=connManager.getActiveNetworkInfo();
//        if(networkInfo!=null){
//            if(networkInfo.isAvailable()){
//                netState=networkInfo.getType();
//            }
//            else
//                netState=NETWORKSTATE_NONE;
//        }
//        else{
//            netState=NETWORKSTATE_NONE;
//        }
        return netState;
    }

}
