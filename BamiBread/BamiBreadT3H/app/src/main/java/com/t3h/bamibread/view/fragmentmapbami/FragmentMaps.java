package com.t3h.bamibread.view.fragmentmapbami;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.t3h.bamibread.PermissionUtils;
import com.t3h.bamibread.R;
import com.t3h.bamibread.managers.Utils;
import com.t3h.bamibread.model.Directions;
import com.t3h.bamibread.model.StoreBamiBread;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class FragmentMaps extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener, IMaps.MapView, GoogleMap.InfoWindowAdapter, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private StoreBamiBread mStoreBamiBread;
    private MapsPresenter mMapsPresenter;
    private Geocoder mGeocoder;

    private LatLng mLatLngStore;
    private LatLng mMyLatLng;
    private Marker mMyMarker;
    private Marker mMarkerStore;
    private boolean iClickMarker;
    private static final String TAG = FragmentMaps.class.getSimpleName();

    public void setStoreBamiBread(StoreBamiBread storeBamiBread) {
        this.mStoreBamiBread = storeBamiBread;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGeocoder = new Geocoder(getContext());
        mMapsPresenter = new MapsPresenter(this);
        if (PermissionUtils.checkPermisson(getContext())) {
            checkGPS();

        }

    }

    public void checkGPS() {
        if (!PermissionUtils.checkGPSGranted(getContext())) {
            //show một dialog lên
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 10);
        } else {
            initMap();
        }
    }

    private void initMap() {
        getMapAsync(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (PermissionUtils.checkGPSGranted(getContext())) {
                initMap();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            for (int grant : grantResults) {
                if (grant == PackageManager.PERMISSION_GRANTED) {
                    checkGPS();
                }
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        mLatLngStore = Utils.coverAddress(mStoreBamiBread.getNameStore(), mGeocoder);
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null){
            Toast.makeText(getContext(),"Please kết nối in",Toast.LENGTH_LONG).show();
            return;
        }
        CameraPosition cameraPosition = CameraPosition.fromLatLngZoom(mLatLngStore, 16);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cameraUpdate);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.snippet(mStoreBamiBread.getNameStore());
        markerOptions.title("Test");
        markerOptions.position(mLatLngStore);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMarkerStore = mMap.addMarker(markerOptions);
        mMap.setInfoWindowAdapter(FragmentMaps.this);
        mMarkerStore.showInfoWindow();

        mMap.setOnMarkerClickListener(FragmentMaps.this);


        mMap.setOnMyLocationChangeListener(this);

    }

    @Override
    public void onMyLocationChange(Location location) {
        if (mMyMarker == null) {
            mMyLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(mMyLatLng);
            markerOptions.snippet(Utils.getAddresFromLatLng(mMyLatLng, mGeocoder));
            markerOptions.title("Test");
            mMyMarker = mMap.addMarker(markerOptions);
        } else {
            mMyMarker.setPosition(mMyLatLng);
        }
        // mMapsPresenter.getDirections(Utils.getAddresFromLatLng(mMyLatLng, mGeocoder), mStoreBamiBread.getNameStore());

        String mUrl=Utils.getUrlFromGoogle(getContext(),mMyLatLng,mLatLngStore);
        mMapsPresenter.drawRoad(mUrl);






    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.info_marker, null);
        TextView textView = (TextView) view.findViewById(R.id.txt_name_store_bami);
        ImageView img = view.findViewById(R.id.img_);
        textView.setText(mStoreBamiBread.getNameStore());

        if (iClickMarker) {
            textView.setText(Utils.getAddresFromLatLng(mMyLatLng, mGeocoder));
            img.setImageResource(R.drawable.unnamed);


        } else {
            textView.setText(mStoreBamiBread.getNameStore());
            img.setImageResource(R.drawable.icon_app);
        }

        return view;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(mMyMarker)) {
            iClickMarker = true;
            mMap.setInfoWindowAdapter(FragmentMaps.this);

        } else if (marker.equals(mMarkerStore)) {
            iClickMarker = false;
            mMap.setInfoWindowAdapter(FragmentMaps.this);
        }

        return false;
    }

    @Override
    public void finishgetDirection(Directions directions) {
        //List<String> listPoints = new ArrayList<>();
        if (directions != null) {
            Log.d(TAG, "finishgetDirection: " + directions.getRoutes().get(0).getLeg().get(0).getSteps().get(0));
            directions.getRoutes().get(0).getLeg().get(0).getSteps().size();
            for (int i = 0; i < directions.getRoutes().get(0).getLeg().get(0).getSteps().size(); i++) {
                Directions.Step objec = directions.getRoutes().get(0).getLeg().get(0).getSteps().get(i);
                Directions.Polyline p = objec.getPolyline();
                String points = p.getPoints();
                Log.d(TAG, "finishgetDirection: " + points);
                //listPoints.add(points);
                PolylineOptions polyLine = new PolylineOptions();
                List<LatLng> listLatLng = Utils.decodePoly(points);
                for (LatLng listL : listLatLng) {
                    polyLine.add(listL);
                    polyLine.width(10);
                    polyLine.color(Color.RED);
                }
                if (polyLine != null) {
                    mMap.addPolyline(polyLine);
                }
            }

        }


    }

    @Override
    public void errorgetDirection(Throwable throwable) {
        Log.d(TAG, "errorgetDirection: " + throwable);

    }

    @Override
    public void drawDirection(List<String> lists) {
        PolylineOptions lineOptions = new PolylineOptions();
        for (int i = 0; i < lists.size(); i++) {
            String p = lists.get(i);
            List<LatLng> points = Utils.decodePoly(p);
            for (LatLng l : points) {
                lineOptions.add(l);
                lineOptions.width(10);
                lineOptions.color(Color.RED);
            }
        }
        if (lineOptions != null) {
            mMap.addPolyline(lineOptions);
        }

    }
}
