package com.t3h.bamibread.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.t3h.bamibread.interfaces.IViewMain;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/1/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements IViewMain {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutMain());
        mUnbinder = ButterKnife.bind(this);
        initComponents();
        addEvents();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
