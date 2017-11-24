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
 * Created by Heart Of Dead on 9/13/2017.
 */

public class ManagerRegister {
    private static ManagerRegister mRegister = new ManagerRegister();

    public static ManagerRegister getIntance() {
        return mRegister;
    }

    private boolean checkUser;

    public boolean isCheckUser() {
        return checkUser;
    }

    public void setCheckUser(boolean checkUser) {
        this.checkUser = checkUser;
    }


    public Observable<Boolean> checkUserOb(String text){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                String url = Contants.SEVER_NAME+"user?username="+text;
                URL murl = new URL(url);
                InputStreamReader in = new InputStreamReader(murl.openStream(), "UTF-8");
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

    public Observable<Boolean>registerOb(String name,String username,String pass){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                ///  http://192.168.1.221/heartofdead/bamibread/user?username=xuanhoinguyen1&password=123456&name=Nguyễn Thị Tèo

                String param="?username="+ URLEncoder.encode(username)+"&password="+URLEncoder.encode(pass)+"&name="+URLEncoder.encode(name);
                String url = Contants.SEVER_NAME+"user"+param;
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
