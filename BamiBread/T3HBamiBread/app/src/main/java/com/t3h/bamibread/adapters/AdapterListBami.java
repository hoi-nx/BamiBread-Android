package com.t3h.bamibread.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IListBami;
import com.t3h.bamibread.model.BamiBread;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class AdapterListBami extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IListBami iList;

    public AdapterListBami(IListBami iList) {
        this.iList = iList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_bami_bread, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        BamiBread bamiBread= (BamiBread) iList.getData(position);
        myViewHolder.imgBami.setImageResource(bamiBread.getImgAddress());
        myViewHolder.txtAddress.setText(bamiBread.getAddress());

        myViewHolder.itemView.setOnClickListener(view -> {
            iList.onClickItemView(position);
        });

    }

    @Override
    public int getItemCount() {
        return iList.count();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_bami)
        CircleImageView imgBami;
        @BindView(R.id.txtAddress)
        TextView txtAddress;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
