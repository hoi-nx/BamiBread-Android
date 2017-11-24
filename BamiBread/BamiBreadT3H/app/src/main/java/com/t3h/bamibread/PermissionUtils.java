package com.t3h.bamibread;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v13.app.ActivityCompat;
import android.Manifest;

import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class PermissionUtils {
    public static boolean checkPermisson(Context context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String>listPermisson=new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
           listPermisson.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(listPermisson.size()==0){
            return true;
        }
        int index=0;
        String permisson[]=new String[listPermisson.size()];
        for(String list:listPermisson){
            permisson[index]=list;
            index++;
        }
        ActivityCompat.requestPermissions((Activity) context,permisson,100);
        return false;
    }

    public static boolean checkGPSGranted(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isOpenGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isOpenNetWork = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return isOpenGPS && isOpenNetWork;
    }

}
