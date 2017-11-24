package com.t3h.bamibread.view.fragmentparent;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Heart Of Dead on 9/21/2017.
 */

public class ParentPresenter implements IParent.PresenterParent {
    @Override
    public void saveLogOut(Context context, boolean iClickLogOut) {
        SharedPreferences mShare = context.getSharedPreferences("LOGINCLICK", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mShare.edit();
        mEditor.putBoolean("LOGINCLICK", iClickLogOut);
        mEditor.commit();
    }
}
