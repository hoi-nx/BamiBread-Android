package com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment.fragmentrecruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t3h.bamibread.NewsDetailActivity;
import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterRcNewsBami;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.interfaces.IListNew;
import com.t3h.bamibread.interfaces.IViewParent;
import com.t3h.bamibread.model.NewsBamiBread;
import com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment.fragmentnews.NewsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class FragmentRecruitment extends BaseFragment implements IViewParent, IListNew {
    @BindView(R.id.rc_recruit_bami)
    RecyclerView mRcRecruit;


    private NewsPresenter newsPresenter;
    private List<NewsBamiBread> newsBamiBreadList;
    private AdapterRcNewsBami mAdapterNewsBami;

    @Override
    public int getLayoutMain() {
        return R.layout.fragment_recruitment_layout;
    }

    @Override
    public void initComponents() {
        newsPresenter = new NewsPresenter(this);
        newsPresenter.getPostBamiBread(getContext(), "/452001171661142");
    }

    @Override
    public void addEvents() {

    }


    @Override
    public void finish(Object value) {
        newsBamiBreadList = (List<NewsBamiBread>) value;
        mAdapterNewsBami=new AdapterRcNewsBami(this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        mRcRecruit.setLayoutManager(layoutManager);
        mRcRecruit.setAdapter(mAdapterNewsBami);
        mAdapterNewsBami.notifyDataSetChanged();
    }

    @Override
    public void error(Throwable error) {

    }

    @Override
    public int count() {
        return newsBamiBreadList==null?0:newsBamiBreadList.size();
    }

    @Override
    public Object getData(int position) {
        return newsBamiBreadList.get(position);
    }


    @Override
    public void onClickItem(int position) {
        NewsBamiBread newsBamiBread=newsBamiBreadList.get(position);
        Intent intent=new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("NEWBAMI",newsBamiBread);
        startActivity(intent);

    }
}
