package com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterViewPagerNewsAndRecruit;
import com.t3h.bamibread.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class FragmentParentNewsAndRecruit extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpage_news_and_recruit)
    ViewPager mViewPager;

    private AdapterViewPagerNewsAndRecruit mAdapterViewPagerNewsAndRecruit;


    @Override
    public int getLayoutMain() {
        return R.layout.fragment_recruit_and_news;
    }

    @Override
    public void initComponents() {
        mAdapterViewPagerNewsAndRecruit=new AdapterViewPagerNewsAndRecruit(getChildFragmentManager());
        mViewPager.setAdapter(mAdapterViewPagerNewsAndRecruit);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public void addEvents() {

    }


}
