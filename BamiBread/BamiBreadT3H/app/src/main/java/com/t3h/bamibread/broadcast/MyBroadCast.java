package com.t3h.bamibread.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Heart Of Dead on 9/21/2017.
 */

public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        switch (action){
            case "android.net.conn.CONNECTIVITY_CHANGE":
                ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null){
                    MySystem.getIntance().makeChangeInternet(true);
                }else {
                    Toast.makeText(context,"Mất kết nối mạng",Toast.LENGTH_LONG).show();
                    MySystem.getIntance().makeChangeInternet(false);
                }
                break;
        }
    }

}
