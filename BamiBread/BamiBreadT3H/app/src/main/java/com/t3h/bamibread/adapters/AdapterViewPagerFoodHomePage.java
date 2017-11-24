package com.t3h.bamibread.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IList;
import com.t3h.bamibread.interfaces.IListFastFood;
import com.t3h.bamibread.model.FastFood;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class AdapterViewPagerFoodHomePage extends PagerAdapter {
    private IListFastFood iListFastFood;
    private Activity activity;

    public AdapterViewPagerFoodHomePage(IListFastFood iListFastFood, Activity activity) {
        this.iListFastFood = iListFastFood;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return iListFastFood.countList();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.item_viewpager_food, container, false);
        ImageView imgFood = itemView.findViewById(R.id.image_food);
        TextView txtName = itemView.findViewById(R.id.txt_name_food);
        TextView txtPrice = itemView.findViewById(R.id.txt_price);
        ShineButton orderFood = itemView.findViewById(R.id.order_food);
        ImageButton shareButton = itemView.findViewById(R.id.share_button);
        ShineButton favorite = itemView.findViewById(R.id.favorite);

        orderFood.init(activity);


//        LinearLayout linearLayout = itemView.findViewById(R.id.wrapper);
       favorite.init(activity);
//        ShineButton shineButtonJava = new ShineButton(activity);
//        shineButtonJava.setBtnColor(Color.GRAY);
//        shineButtonJava.setBtnFillColor(Color.RED);
//        shineButtonJava.setShapeResource(R.raw.heart);
//        shineButtonJava.setAllowRandomColor(true);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
//        shineButtonJava.setLayoutParams(layoutParams);
//        if (linearLayout != null) {
//            linearLayout.addView(shineButtonJava);
//        }
        FastFood fastFood = iListFastFood.getDataFF(position);
        Picasso.with(itemView.getContext()).load(fastFood.getUrlImage()).placeholder(R.drawable.demobanhmi).error(R.drawable.demobanhmi).into(imgFood);
        txtName.setText(fastFood.getName());
        txtPrice.setText(fastFood.getPrice() + " đ");


        orderFood.setOnClickListener(view -> {
            iListFastFood.clickOrderFood(position);
        });
        container.addView(itemView);
        return itemView;
    }
}
