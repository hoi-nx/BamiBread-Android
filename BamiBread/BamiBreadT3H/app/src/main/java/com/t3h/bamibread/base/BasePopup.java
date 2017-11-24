package com.t3h.bamibread.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.t3h.bamibread.interfaces.IViewMain;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public abstract class BasePopup extends Dialog implements IViewMain {
    private Unbinder mUnbinder;
    public BasePopup(@NonNull Context context) {
        super(context);
        initDialog();
        mUnbinder= ButterKnife.bind(this);
    }
    private void initDialog(){
        setContentView(getLayoutMain());
        setCanceledOnTouchOutside(false);
        initComponents();
        addEvents();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUnbinder.unbind();
    }
}
