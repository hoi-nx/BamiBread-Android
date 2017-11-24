package com.t3h.bamibread.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IList;
import com.t3h.bamibread.interfaces.IListFastFood;
import com.t3h.bamibread.model.FastFood;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class AdapterTopHomePage extends PagerAdapter {
    private IList iList;

    public AdapterTopHomePage(IList iList) {
       this.iList=iList;
    }

    @Override
    public int getCount() {
        return  iList.count();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView=LayoutInflater.from(container.getContext()).inflate(R.layout.item_top_home_page,container,false);
        ImageView imgTop=itemView.findViewById(R.id.img_top_home);
        Picasso.with(itemView.getContext()).load((String) iList.getData(position)).resize(600,600).placeholder(R.drawable.demo).error(R.drawable.demo).into(imgTop);
        container.addView(itemView);
        return itemView;
    }
}
