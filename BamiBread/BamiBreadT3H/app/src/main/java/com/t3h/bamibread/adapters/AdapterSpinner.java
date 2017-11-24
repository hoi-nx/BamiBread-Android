package com.t3h.bamibread.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IList;
import com.t3h.bamibread.interfaces.IListNew;
import com.t3h.bamibread.model.StoreBamiBread;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class AdapterSpinner extends BaseAdapter {
    private IList iList;

    public AdapterSpinner(IList iList) {
       this.iList=iList;
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
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        view=layoutInflater.inflate(R.layout.item_spinner,viewGroup,false);
        TextView textView=view.findViewById(R.id.txt_store_spinner);
        ImageView imageView=view.findViewById(R.id.img_item_spinner);
        StoreBamiBread storeBamiBread= (StoreBamiBread) iList.getData(i);
        textView.setText(storeBamiBread.getNameStore());
        Picasso.with(view.getContext()).load(storeBamiBread.getLinkImg()).into(imageView);
        return view;
    }
}
