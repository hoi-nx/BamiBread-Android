package com.t3h.bamibread;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.t3h.bamibread.base.BaseActivity;
import com.t3h.bamibread.model.StoreBamiBread;
import com.t3h.bamibread.view.fragmentmapbami.FragmentMaps;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class MapsActivity extends BaseActivity {
    FragmentMaps mapFragment;
    @Override
    public int getLayoutMain() {
        return R.layout.map_activity;
    }

    @Override
    public void initComponents() {
        openMapFragment();
    }

    @Override
    public void addEvents() {

    }

    private void openMapFragment() {
        mapFragment = new FragmentMaps();
        mapFragment.setStoreBamiBread(getStoreBami());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_activity, mapFragment, mapFragment.getClass().getName());
        fragmentTransaction.commit();
    }

    public StoreBamiBread getStoreBami() {
        Intent intent = getIntent();
        StoreBamiBread storeBamiBread = (StoreBamiBread) intent.getSerializableExtra("STORE");
        return storeBamiBread;
    }
}
