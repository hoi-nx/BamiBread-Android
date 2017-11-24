package com.t3h.bamibread.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IList;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public class AdapterTopHomePage extends BaseAdapter {
    private IList iList;

    public AdapterTopHomePage(IList iList) {
        this.iList = iList;
    }

    @Override
    public int getCount() {
        return iList.count();
    }

    @Override
    public Object getItem(int i) {
        return iList.getData(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            view = layoutInflater.inflate(R.layout.layout_item, viewGroup, false);
            ImageView imageView = view.findViewById(R.id.img_top_home_page);
            Picasso.with(viewGroup.getContext()).load((String) iList.getData(i)).resize(800, 800).into(imageView);
        }
        return view;
    }
}
