package com.t3h.bamibread.view.fragmentchild.fragmentstore;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.bamibread.R;
import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class FragmentInformationStore extends BaseFragment {


    @BindView(R.id.img_map_bami)
    ImageView imgMapBami;
    @BindView(R.id.name_address)
    TextView mAddress;
    @BindView(R.id.txt_phone_store)
    TextView mPhoneStore;



    @Override
    public int getLayoutMain() {
        return R.layout.fragment_info_bami_bread;
    }

    @Override
    public void initComponents() {
        Bundle bundle = getArguments();
        if(bundle==null){
            return;
        }
        mPhoneStore.setText(bundle.getString("PHONE"));
        mAddress.setText(bundle.getString("ADDRESS"));
        imgMapBami.setImageResource(bundle.getInt("IMGBM"));

    }

    @Override
    public void addEvents() {

    }


    @OnClick(R.id.img_map_bami)
    public void onViewClicked() {

        FragmentMapStore mapFragment=new FragmentMapStore ();
        BaseFragment.openFragment(getFragmentManager(),getFragmentManager().beginTransaction(),mapFragment,ScreenAnimation.NONE,R.id.frame_layout_child,false,true);
//        FragmentManager fragmentManager=getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frame_layout_child,mapFragment,mapFragment.getClass().getName());
//        fragmentTransaction.commit();
        //open map

    }


}
