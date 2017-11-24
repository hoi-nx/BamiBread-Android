package com.t3h.bamibread.managers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.t3h.bamibread.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 9/19/2017.
 */

public class Utils {
    public static String getLinkSer(String link) {
        String data = "";

        try {
            URL url = new URL(link);
            InputStream in = url.openStream();
            InputStreamReader strem = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(strem);
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
            return data;
        }
    public static List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    public static String getUrlFromGoogle(Context context, LatLng origin, LatLng dest) {


        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;


        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        String key = context.getString(R.string.key_google_map);


        String parameters = str_origin + "&" + str_dest + "&" + key;


        String output = "json";


        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;

    }
    public static String getAddresFromLatLng(LatLng latLng, Geocoder mGeocoder) {
        try {
            List<Address> addresses = mGeocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
                int line = addresses.get(0).getMaxAddressLineIndex();
                String ad = addresses.get(0).getAddressLine(0);

                //Log.d(TAG, "getAddress: =====" + ad);

                for (int i = 1; i < line; i++) {
                    ad = ad + "; " + addresses.get(0).getAddressLine(i);
                }
                return ad;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Khoong tim thaay";
    }

    public static LatLng coverAddress(String address, Geocoder mGeocoder) {
        if (address != null && !address.isEmpty()) {
            try {
                List<Address> addressList = mGeocoder.getFromLocationName(address, 1);

                if (addressList != null && addressList.size() > 0) {
                    double lat = addressList.get(0).getLatitude();
                    double lng = addressList.get(0).getLongitude();
                    //Log.d(TAG, "convertAddress: =====" + lat);
                    LatLng latLng = new LatLng(lat, lng);
                    return latLng;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
