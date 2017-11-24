package com.t3h.bamibread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Heart Of Dead on 9/19/2017.
 */

public class AdapterTabLayout extends FragmentPagerAdapter {
    public AdapterTabLayout(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentOderShip();
            default:
                return new FragmentMPChart();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Order Ship";
            default:
                return "Thông Kê";

        }
    }
}
