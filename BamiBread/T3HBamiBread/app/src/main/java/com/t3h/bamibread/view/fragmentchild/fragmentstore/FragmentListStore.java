package com.t3h.bamibread.view.fragmentchild.fragmentstore;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterListBami;
import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.interfaces.IListBami;
import com.t3h.bamibread.managers.ManagerBamiBread;
import com.t3h.bamibread.model.BamiBread;

import butterknife.BindView;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class FragmentListStore extends BaseFragment implements IListBami {
    @BindView(R.id.rc_list_bami)
    RecyclerView rcListBami;

    private AdapterListBami mAdapterListBami;

    @Override
    public int getLayoutMain() {
        return R.layout.fragment_list_bami_store;
    }

    @Override
    public void initComponents() {
        mAdapterListBami = new AdapterListBami(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcListBami.setLayoutManager(layoutManager);
        rcListBami.setAdapter(mAdapterListBami);
        rcListBami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"demo",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void addEvents() {

    }


    @Override
    public int count() {
        return ManagerBamiBread.getInstance().getListBami().size();
    }

    @Override
    public BamiBread getData(int position) {
        return ManagerBamiBread.getInstance().getListBami().get(position);
    }

    @Override
    public void onClickItemView(int position) {
        BamiBread bamiBread=ManagerBamiBread.getInstance().getListBami().get(position);
        Bundle bundle=new Bundle();
        bundle.putInt("IMGBM",bamiBread.getImgMap());
        bundle.putString("ADDRESS",bamiBread.getAddress());
        bundle.putString("PHONE",bamiBread.getPhone());
        BaseFragment.hideFragment(getFragmentManager(),getFragmentManager().beginTransaction(),FragmentListStore.class,ScreenAnimation.NONE,false,true);
        BaseFragment.openFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentInformationStore.class, ScreenAnimation.OPEN_FULL, bundle, R.id.frame_layout_child, false, true);

    }
}
