package com.t3h.bamibread.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.t3h.bamibread.R;

/**
 * Created by Heart Of Dead on 9/21/2017.
 */

public class PopupHotline extends Dialog {
    public PopupHotline(@NonNull Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        setContentView(R.layout.pop_up_hotline);
        setCanceledOnTouchOutside(false);
        findViewById(R.id.btnClose).setOnClickListener(view -> {
            dismiss();
        });
        findViewById(R.id.btnOk).setOnClickListener(view -> {
//
            String numberPhone="0988872930";
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+numberPhone));
            getContext().startActivity(intent);
        });
    }
}
