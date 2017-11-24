package com.t3h.bamibread.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.IListNew;
import com.t3h.bamibread.model.NewsBamiBread;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class AdapterRcNewsBami extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IListNew iListNew;

    public AdapterRcNewsBami(IListNew iListNew) {
        this.iListNew = iListNew;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_and_recruitment, parent, false);

        return new MyHolder(itemview);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        NewsBamiBread newsBamiBread= (NewsBamiBread) iListNew.getData(position);
        myHolder.txtMessage.setText(newsBamiBread.getMessage());
        Picasso.with(myHolder.itemView.getContext()).load(newsBamiBread.getLinkImage()).error(R.drawable.demo).placeholder(R.drawable.demo).into(myHolder.imageView);
        holder.itemView.setOnClickListener(view -> {
            iListNew.onClickItem(position);
        });

    }

    @Override
    public int getItemCount() {
        return iListNew.count();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView txtMessage;
        private ImageButton favoriteButton, shareButton;
        private ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_message);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
            shareButton = itemView.findViewById(R.id.share_button);
            imageView=itemView.findViewById(R.id.card_image);
        }
    }
}
