package com.t3h.bamibread.managers;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Heart Of Dead on 9/8/2017.
 */

public class ManagerLogin {
    private static ManagerLogin mManagerLogin;
    private static ManagerLogin managerLogin = new ManagerLogin();

    public ManagerLogin getManagerLogin() {
        return managerLogin;
    }

    public static ManagerLogin getInstance() {
        if (mManagerLogin == null) {
            mManagerLogin = new ManagerLogin();
        }
        return mManagerLogin;
    }

    public HashMap<String, String> getInforUser(AccessToken accessToken) {
        HashMap<String, String> mHashMap = new HashMap<>();

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Insert your code here
                        try {
                            mHashMap.put("NAME", object.getString("name").toString());
                            mHashMap.put("EMAIL", object.getString("email").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,birthday");
        request.setParameters(parameters);
        request.executeAsync();
        return mHashMap;

    }

    public Observable<Boolean> checkLoginSer(String username, String password){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                ///  http://192.168.1.221/heartofdead/bamibread/user?username=xuanhoinguyen1&password=123456&name=Nguyễn Thị Tèo
                String url = "http://192.168.1.221/heartofdead/bamibread/user?username="+username+"&password="+password;
                URL murl = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) murl.openConnection();
                httpURLConnection.setRequestMethod("GET");
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
