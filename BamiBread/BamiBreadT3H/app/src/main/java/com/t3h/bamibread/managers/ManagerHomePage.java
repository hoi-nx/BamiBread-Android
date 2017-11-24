package com.t3h.bamibread.managers;

import android.util.Log;

import com.t3h.bamibread.interfaces.Contants;
import com.t3h.bamibread.model.FastFood;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class ManagerHomePage {
    private static ManagerHomePage mHomePage = new ManagerHomePage();

    public static ManagerHomePage getIntance() {
        return mHomePage;
    }

    public Observable<List<String>> getListImg() {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                List<String> listImg = new ArrayList<String>();

                Document document = Jsoup.connect("http://bamibread.com/ve-bami-bread/").get();
//                Elements el = document.select("header.mobile-left-logo clearfix");
//                String img = el.select("div.logo-left has-img clearfix").select("a").select("img.standard").attr("src");
//                Log.d("Interactor", "doInBackground: " + img);
                Elements element = document.select("div.img-wrap");
                for (Element ele : element) {
                    String linkImg = ele.select("img").attr("src");
                    listImg.add(linkImg);
                    Log.d("Interactor", "doInBackground: " + linkImg);

                }

                e.onNext(listImg);
            }
        });
    }

    public Observable<List<FastFood>> getListFastFood() {
        return Observable.create(new ObservableOnSubscribe<List<FastFood>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FastFood>> e) throws Exception {
                List<FastFood> listFastFood = new ArrayList<FastFood>();
                String link = Contants.SEVER_NAME+"fastfood";
                try {
                    JSONArray jsonArray=new JSONArray(Utils.getLinkSer(link));
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("Name");
                        String linkImg=jsonObject.getString("Image");
                        String price=jsonObject.getString("Price");
                        FastFood fastFood=new FastFood(name,Integer.parseInt(price),linkImg);
                        listFastFood.add(fastFood);
                    }
                } catch (Exception ex) {

                }

                e.onNext(listFastFood);
            }
        });
    }

    public Observable<List<String>> getListIntroduce() {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                List<String> listImg = new ArrayList<String>();
                String link = Contants.SEVER_NAME+"introduce";
                try {
                    JSONArray jsonArray=new JSONArray(Utils.getLinkSer(link));
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String linkImage=jsonObject.getString("LinkImage");

                        listImg.add(linkImage);
                    }
                } catch (Exception ex) {

                }

                e.onNext(listImg);
            }
        });
    }


}
