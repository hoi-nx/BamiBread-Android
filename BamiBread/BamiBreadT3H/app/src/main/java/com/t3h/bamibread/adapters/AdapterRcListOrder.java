package com.t3h.bamibread.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IListOrder;
import com.t3h.bamibread.model.OrderFood;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class AdapterRcListOrder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IListOrder iListOrder;

    public AdapterRcListOrder(IListOrder iListOrder) {
        this.iListOrder = iListOrder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_bami_shopping_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        OrderFood orderFood=iListOrder.getOrder(position);
        myHolder.txtNameFood.setText(orderFood.getName());
        myHolder.txtTotalPrice.setText((orderFood.getPrice()*orderFood.getNumber())+"đ");
        myHolder.txtNumberFood.setText(orderFood.getNumber()+"");
        Picasso.with(myHolder.itemView.getContext()).load(orderFood.getUrlImage()).into(myHolder.imgIcon);
        myHolder.imgDelete.setOnClickListener(view -> {
            iListOrder.deleteOrderFood(position);
        });
        myHolder.btnCountUp.setOnClickListener(view -> {
            iListOrder.countUpClick(position);
        });
        myHolder.btnCountDown.setOnClickListener(view -> {
            iListOrder.countDownClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return iListOrder.count();
    }
    public class  MyHolder extends RecyclerView.ViewHolder{
        private ImageView imgIcon,imgDelete;
        private Button btnCountDown,btnCountUp;
        private TextView txtNameFood,txtTotalPrice,txtNumberFood;

        public MyHolder(View itemView) {
            super(itemView);
            imgIcon=itemView.findViewById(R.id.img_bami);
            imgDelete=itemView.findViewById(R.id.img_delete);
            txtNameFood=itemView.findViewById(R.id.txt_name_bami);
            txtTotalPrice=itemView.findViewById(R.id.total_price);
            txtNumberFood=itemView.findViewById(R.id.numberof_);
            btnCountDown=itemView.findViewById(R.id.count_down);
            btnCountUp=itemView.findViewById(R.id.count_up);
        }
    }
}
