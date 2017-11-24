package com.t3h.bamibread.view.fragmentmapbami;

import android.content.Context;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;
import com.t3h.bamibread.model.Directions;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public interface IMaps {
    interface MapView{
       void finishgetDirection(Directions directions);
        void errorgetDirection(Throwable throwable);

        void drawDirection(List<String>list);

    }
    interface PresenterMaps{
       void drawRoad(String url);
        void getDirections(String origin,String dest);
    }
}
