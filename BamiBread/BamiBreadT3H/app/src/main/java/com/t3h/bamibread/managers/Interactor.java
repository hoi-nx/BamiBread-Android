package com.t3h.bamibread.managers;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.t3h.bamibread.interfaces.Contants;
import com.t3h.bamibread.model.Directions;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class Interactor {
    private static Interactor interactor=new Interactor();

    public static Interactor getInteractor() {
        return interactor;
    }

    private ResApiGoogleMap resApiGoogleMap;
    public Interactor() {
        OkHttpClient.Builder http =
                new OkHttpClient.Builder()
                        .readTimeout(2, TimeUnit.MINUTES)
                        .writeTimeout(2, TimeUnit.MINUTES)
                        .connectTimeout(2, TimeUnit.MINUTES);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://maps.googleapis.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(http.build());
        resApiGoogleMap = builder.build().create(ResApiGoogleMap.class);
    }
    public Observable<Directions> findLocation(String origin,
                                               String destination) {
        return resApiGoogleMap.direction(origin, destination,
                Contants.KEY_GOOGLE_MAP)
                //tuong tac voi server tren thread khac
                .subscribeOn(Schedulers.newThread())
                //sau khi tuong tac voi server,
                // tra ve ket qua tren main threa
                .observeOn(AndroidSchedulers.mainThread());

    }
}
