package com.t3h.bamibread.view.fragmentuseguide;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterViewPagerUseGuide;
import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.interfaces.IList;
import com.t3h.bamibread.managers.ManagerUseGuide;
import com.t3h.bamibread.view.fragmentlogin.FragmentLogin;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public class FragmentUseGuide extends BaseFragment implements IList, IUseGuide.ViewGuide {
    @BindView(R.id.hozi_)
    HorizontalInfiniteCycleViewPager mHoziontall;
    @BindView(R.id.btn_skip)
    AppCompatButton btnSkip;

    private AdapterViewPagerUseGuide mAdapterViewPagerUseGuide;
    private UseGuidePresenter mUseGuidePresenter;
    @Override
    public int getLayoutMain() {
        return R.layout.use_guide_layout;
    }



    @Override
    public void initComponents() {
        mAdapterViewPagerUseGuide=new AdapterViewPagerUseGuide(this);
        mHoziontall.setAdapter(mAdapterViewPagerUseGuide);



    }

    @Override
    public void addEvents() {
        btnSkip.setOnClickListener(v->{
            mUseGuidePresenter=new UseGuidePresenter(this);
            mUseGuidePresenter.saveSharedPreferences(getContext(),true);
            BaseFragment.removeFragment(getFragmentManager(),getFragmentManager().beginTransaction(), FragmentUseGuide.class.getName(), ScreenAnimation.NONE, false, true);
            BaseFragment.openFragment(getFragmentManager(),getFragmentManager().beginTransaction(), FragmentLogin.class, ScreenAnimation.NONE, null, R.id.ln_activity, false, true);


        });

    }


    @Override
    public int count() {
        return ManagerUseGuide.getInstance().getImageUseGuide().size();
    }

    @Override
    public Object getData(int position) {
        return ManagerUseGuide.getInstance().getImageUseGuide().get(position);
    }


}
