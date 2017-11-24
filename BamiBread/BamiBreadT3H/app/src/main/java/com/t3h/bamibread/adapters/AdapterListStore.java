package com.t3h.bamibread.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IListStoreBami;
import com.t3h.bamibread.model.StoreBamiBread;
import com.t3h.bamibread.view.fragmentchild.fragmentliststore.IListStore;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class AdapterListStore extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IListStoreBami iListStore;

    public AdapterListStore(IListStoreBami iListStore) {
        this.iListStore = iListStore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_store,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        StoreBamiBread storeBamiBread= (StoreBamiBread) iListStore.getData(position);
        myViewHolder.txtNameStore.setText(storeBamiBread.getNameStore());
        myViewHolder.txtPhone.setText(storeBamiBread.getPhone());
        Picasso.with(myViewHolder.itemView.getContext()).load(storeBamiBread.getLinkImg()).into(myViewHolder.imgStore);
        myViewHolder.itemView.setOnClickListener(view -> {
            iListStore.onClickItem(position);
        });

    }

    @Override
    public int getItemCount() {
        return iListStore.count();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgStore;
        private TextView txtNameStore;
        private TextView txtPhone;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgStore=itemView.findViewById(R.id.img_store);
            txtNameStore=itemView.findViewById(R.id.txt_name_store);
            txtPhone=itemView.findViewById(R.id.txt_phone_store);
        }
    }
}
