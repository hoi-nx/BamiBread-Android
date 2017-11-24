package com.t3h.bamibread.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.t3h.bamibread.R;
import com.t3h.bamibread.base.BasePopup;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class CustomPopupCallHotline extends BasePopup {
    @BindView(R.id.close_popup)
    AppCompatButton closePopup;
    @BindView(R.id.call)
    AppCompatButton call;

    public CustomPopupCallHotline(@NonNull Context context) {
        super(context);
    }

    @OnClick({R.id.close_popup, R.id.call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_popup:
                dismiss();
                break;
            case R.id.call:
                dismiss();
                String numberPhone="0988872930";
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+numberPhone));
                getContext().startActivity(intent);
                break;
        }
    }

    @Override
    public int getLayoutMain() {
        return R.layout.popup_call_hotline;
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void addEvents() {

    }
}
