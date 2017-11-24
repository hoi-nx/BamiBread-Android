package com.t3h.bamibread.managers;

import android.util.Log;

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
 * Created by Heart Of Dead on 9/13/2017.
 */

public class ManagerHomePage {
    private static ManagerHomePage mHomePage = new ManagerHomePage();

    public static ManagerHomePage getInstance() {
        return mHomePage;
    }


    public Observable<List<String>> getListImg() {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                List<String> listImg = new ArrayList<String>();

                Document document = Jsoup.connect("http://bamibread.com/ve-bami-bread/").get();
                Elements el = document.select("header.mobile-left-logo clearfix");
                String img = el.select("div.logo-left has-img clearfix").select("a").select("img.standard").attr("src");
                Log.d("Interactor", "doInBackground: " + img);
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
}


