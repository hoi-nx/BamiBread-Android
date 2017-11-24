package com.t3h.bamibread.view.fragmentchild.fragmenthomepage;

import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterTopHomePage;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.interfaces.IList;

import java.util.List;


import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class FragmentHomePage extends BaseFragment implements IHomePage.HomePageView, IList {


    private HomePagePresenter mPresenter;
    private List<String> listImage;



    @Override
    public int getLayoutMain() {
        return R.layout.fragment_home_page;
    }

    @Override
    public void initComponents() {
        mPresenter = new HomePagePresenter(this);
        mPresenter.getListImage();



    }

    @Override
    public void addEvents() {

    }


    @Override
    public void finishGetListImg(List<String> listImg) {
        listImage = listImg;
    }

    @Override
    public void errorGetString(Throwable error) {

    }

    @Override
    public int count() {
        return listImage == null ? 0 : listImage.size();
    }

    @Override
    public Object getData(int position) {
        return listImage.get(position);
    }
}
