package com.t3h.bamibread.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment.fragmentnews.FragmentNews;
import com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment.fragmentrecruit.FragmentRecruitment;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class AdapterViewPagerNewsAndRecruit  extends FragmentPagerAdapter{

    public AdapterViewPagerNewsAndRecruit(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentNews();
            default:
                return new FragmentRecruitment();


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
                return "Tin Tức";
            default:
                return "Tuyển dụng";

        }
    }
}
