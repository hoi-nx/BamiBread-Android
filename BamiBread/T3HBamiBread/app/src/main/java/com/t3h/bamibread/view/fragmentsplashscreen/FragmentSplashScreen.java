package com.t3h.bamibread.view.fragmentsplashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t3h.bamibread.R;
import com.t3h.bamibread.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public class FragmentSplashScreen extends BaseFragment {
    @BindView(R.id.shimmer_view_container)
    ShimmerLayout mShimmerLayout;

    @Override
    public int getLayoutMain() {
        return R.layout.splash_screen;
    }


    @Override
    public void initComponents() {
        mShimmerLayout.startShimmerAnimation();
    }

    @Override
    public void addEvents() {

    }

}
