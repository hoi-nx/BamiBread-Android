package com.t3h.bamibread.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IList;
import com.t3h.bamibread.managers.ManagerIntroduceStore;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/12/2017.
 */

public class AdapterRcIntroduceStore extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IList iList;
    public AdapterRcIntroduceStore(IList iList) {
        this.iList = iList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_introduce, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder holder1 = (Holder) holder;
        String link = (String) iList.getData(position);
        if (link == null || link.isEmpty()) {
            return;
        }

        Picasso.with(holder1.itemView.getContext()).load(link).error(R.drawable.demo).placeholder(R.drawable.demo).into(holder1.img);


    }

    @Override
    public int getItemCount() {
        return iList.count();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView img;

        public Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_introduce);
        }
    }
}
