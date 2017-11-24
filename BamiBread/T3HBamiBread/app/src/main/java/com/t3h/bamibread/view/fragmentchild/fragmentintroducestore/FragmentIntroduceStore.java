package com.t3h.bamibread.view.fragmentchild.fragmentintroducestore;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterRcIntroduceStore;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.interfaces.IList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/12/2017.
 */

public class FragmentIntroduceStore extends BaseFragment implements IList, IIntroduceStore.IntroduceView {
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.rc_introduce)
    RecyclerView rcIntroduce;
    private static final String TAG = FragmentIntroduceStore.class.getSimpleName();


    private AdapterRcIntroduceStore adapterRcIntroduceStore;
    private IntroduceStorePresenter introduceStorePresenter;


    private List<String> listString;

    @Override
    public int getLayoutMain() {
        return R.layout.fragment_introduce_store;
    }

    @Override
    public void initComponents() {
        String link = "http://bamibread.com/wp-content/uploads/2016/11/bamibread-website.jpg";
        Picasso.with(getContext()).load(link).error(R.drawable.demo).placeholder(R.drawable.icon_app).into(imgHeader);

        adapterRcIntroduceStore = new AdapterRcIntroduceStore(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcIntroduce.setLayoutManager(manager);
        rcIntroduce.setAdapter(adapterRcIntroduceStore);


    }

    @Override
    public void addEvents() {
       // introduceStorePresenter = new IntroduceStorePresenter(this);
        //introduceStorePresenter.getListImg();
    }


    @Override
    public int count() {
        return listString == null ? 0 : listString.size();
    }

    @Override
    public Object getData(int position) {
        return listString.get(position);
    }


    @Override
    public void finishGetListImg(List<String> listImg) {
        listString = listImg;
        adapterRcIntroduceStore.notifyDataSetChanged();
    }

    @Override
    public void errorGetString(Throwable error) {
        Log.d(TAG, "errorGetString: " + error);

    }


}
