package com.t3h.bamibread;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Heart Of Dead on 9/22/2017.
 */

public class MyViewHolderRc extends RecyclerView.ViewHolder {

public TextView txtName,txtPhone,txtAddress,txtTotal;

    public MyViewHolderRc(View itemView) {
        super(itemView);
        txtName=itemView.findViewById(R.id.txt_name);
        txtAddress=itemView.findViewById(R.id.txt_address);
        txtPhone=itemView.findViewById(R.id.txt_phone);
        txtTotal=itemView.findViewById(R.id.txt_total);

    }
}
