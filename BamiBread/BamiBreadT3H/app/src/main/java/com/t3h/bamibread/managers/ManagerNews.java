package com.t3h.bamibread.managers;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.t3h.bamibread.R;
import com.t3h.bamibread.model.NewsBamiBread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class ManagerNews {
    private static ManagerNews managerNews = new ManagerNews();

    public static ManagerNews getIntance() {
        return managerNews;
    }

    public Observable<List<NewsBamiBread>> getPostBami(Context context, String keyPage) {
        return Observable.create(new ObservableOnSubscribe<List<NewsBamiBread>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsBamiBread>> e) throws Exception {

                List<NewsBamiBread> listNews = new ArrayList<NewsBamiBread>();
                Bundle parameters = new Bundle();
                parameters.putString("fields", "cover,posts{full_picture,message}");
                AccessToken accessToken = new AccessToken(context.getString(R.string.tooken), context.getString(R.string.key_app_facebook), context.getString(R.string.facebook_app_id), null, null, null, null, null);
                GraphRequest request = GraphRequest.newGraphPathRequest(accessToken, keyPage,
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                String url = "";
                                String message="";
                                Log.d("ED", "onCompleted:============= " + response.getJSONObject());
                                if (response.getJSONObject() == null) {
                                    return;
                                }

                                try {
                                    JSONObject jsonObjectCover = response.getJSONObject().getJSONObject("cover");
                                    String cover = jsonObjectCover.getString("source");
                                    JSONObject jsonObjectPost = response.getJSONObject().getJSONObject("posts");
                                    JSONArray jsonArrayData = jsonObjectPost.getJSONArray("data");
                                    for (int i = 0; i < jsonArrayData.length(); i++) {
                                        JSONObject jsonObject = jsonArrayData.getJSONObject(i);
                                        if (jsonObject.has("full_picture")) {
                                            url = jsonObject.getString("full_picture");
                                        }
                                        if(jsonObject.has("message")){
                                           message = jsonObject.getString("message");
                                        }

                                        NewsBamiBread newsBamiBread = new NewsBamiBread(message, url, cover);
                                        listNews.add(newsBamiBread);
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }

//                                GraphRequest nextRequest = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT);
//                                if (nextRequest != null) {
//                                    nextRequest.setParameters(parameters);
//                                    nextRequest.setCallback(this);
//                                    nextRequest.executeAsync();
//
//                                }

                                e.onNext(listNews);

                            }
                        });


                request.setParameters(parameters);
                request.executeAsync();


            }
        });
    }
}
