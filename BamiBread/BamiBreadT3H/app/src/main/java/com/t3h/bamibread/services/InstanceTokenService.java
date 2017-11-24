package com.t3h.bamibread.services;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.t3h.bamibread.interfaces.Contants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Heart Of Dead on 9/20/2017.
 */

public class InstanceTokenService extends FirebaseInstanceIdService {
    public static final String TAG=InstanceTokenService.class.getSimpleName();
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(TAG, "onTokenRefresh: ============");
        String token= FirebaseInstanceId.getInstance().getToken();
        saveToken(token);
    }


    public void saveToken(String token){
        String param="?token="+token;
        String url = Contants.SEVER_NAME+"tokendevice"+param;
        new AsyncTask<String,Void,Boolean>(){

            @Override
            protected Boolean doInBackground(String... strings) {
                try {

                    URL murl = new URL(strings[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) murl.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    InputStreamReader in = new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(in);
                    String line = bufferedReader.readLine();
                    StringBuilder builder = new StringBuilder();
                    while (line != null) {
                        builder.append(line);
                        line = bufferedReader.readLine();
                    }
                    boolean kt=builder.toString().contains("true");
                    return kt;
                }catch (Exception e){

                }


                return null;
            }
        }.execute(url);


    }

}
