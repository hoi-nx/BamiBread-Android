package com.t3h.bamibread.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IList;

/**
 * Created by Heart Of Dead on 8/31/2017.
 */

public class AdapterViewPagerUseGuide extends PagerAdapter {
    private IList iList;
    public AdapterViewPagerUseGuide(IList iList) {
        this.iList = iList;
    }

    @Override
    public int getCount() {
        return iList.count();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater= LayoutInflater.from(container.getContext());
        View itemView=layoutInflater.inflate(R.layout.item_use_guide,container,false);
        ImageView imageView= (ImageView) itemView.findViewById(R.id.img_item);
        imageView.setImageResource((Integer) iList.getData(position));
        container.addView(itemView);
        return itemView;
    }

}
