package com.t3h.bamibread.view.fragmentmapbami;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.managers.Interactor;
import com.t3h.bamibread.managers.Utils;
import com.t3h.bamibread.model.Directions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class MapsPresenter extends BasePresenter<IMaps.MapView> implements IMaps.PresenterMaps {
    private static final String TAG = MapsPresenter.class.getSimpleName();

    public MapsPresenter(IMaps.MapView view) {
        super(view);
    }


    @Override
    public void drawRoad(String url) {
        new AsyncTask<String, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(String... strings) {
                List<String> list = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(Utils.getLinkSer(strings[0]));
                    JSONArray jsonRoutes = jsonObject.getJSONArray("routes");
                    for (int i = 0; i < jsonRoutes.length(); i++) {
                        JSONArray jsonLegs = ((JSONObject) jsonRoutes.get(i)).getJSONArray("legs");
                        for (int j = 0; j < jsonLegs.length(); j++) {
                            String distance = (String) ((JSONObject) ((JSONObject) jsonLegs.get(j)).get("distance")).get("text");
                            Log.d(TAG, "distance: " + distance);
                            JSONArray jsonSteps = ((JSONObject) jsonLegs.get(j)).getJSONArray("steps");
                            for (int k = 0; k < jsonSteps.length(); k++) {
                                double latStart = (double) ((JSONObject) ((JSONObject) jsonSteps.get(k)).get("start_location")).get("lat");
                                double longStart = (double) ((JSONObject) ((JSONObject) jsonSteps.get(k)).get("start_location")).get("lng");

                                double latEnd = (double) ((JSONObject) ((JSONObject) jsonSteps.get(k)).get("end_location")).get("lat");
                                double longEnd = (double) ((JSONObject) ((JSONObject) jsonSteps.get(k)).get("end_location")).get("lng");
                                String polyline = "";
                                polyline = (String) ((JSONObject) ((JSONObject) jsonSteps.get(k)).get("polyline")).get("points");
                                Log.d(TAG, "getDataFromJSon: " + polyline);
                                list.add(polyline);
                            }
                        }

                    }

                } catch (Exception e) {

                }
                return list;
            }

            @Override
            protected void onPostExecute(List<String> lists) {
                super.onPostExecute(lists);
                getMyView().drawDirection(lists);


            }
        }.execute(url);

    }

    @Override
    public void getDirections(String origin,String dest) {
        interact(Interactor.getInteractor().findLocation(origin, dest), new Action<Directions>() {
            @Override
            public void call(Directions directions) {
                getMyView().finishgetDirection(directions);

            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMyView().errorgetDirection(throwable);
            }
        });
    }
}
