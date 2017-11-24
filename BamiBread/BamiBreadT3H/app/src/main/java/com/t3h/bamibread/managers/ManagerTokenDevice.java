package com.t3h.bamibread.managers;

import android.util.Log;

import com.t3h.bamibread.interfaces.Contants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Heart Of Dead on 9/20/2017.
 */

public class ManagerTokenDevice  {
    private static ManagerTokenDevice managerTokenDevice=new ManagerTokenDevice();
    public static ManagerTokenDevice getIntance(){
        return managerTokenDevice;
    }


    public Observable<Boolean> saveToken(String token){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                ///  http://192.168.1.221/heartofdead/bamibread/user?username=xuanhoinguyen1&password=123456&name=Nguyễn Thị Tèo
                String param="?token="+ URLEncoder.encode(token);
                String url = Contants.SEVER_NAME+"tokendevice"+param;
                URL murl = new URL(url);
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

                Log.d("Đang Đê", "subscribe: "+builder.toString());
                e.onNext(builder.toString().contains("true"));
            }
        });
    }
}
