package com.t3h.bamibread.view.fragmentuseguide;

import android.content.Context;
import android.content.SharedPreferences;

import com.t3h.bamibread.base.BasePresenter;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public class UseGuidePresenter extends BasePresenter<IUseGuide.ViewGuide> implements IUseGuide.PresenterUseGuide {
    public UseGuidePresenter(IUseGuide.ViewGuide view) {
        super(view);
    }

    @Override
    public void saveSharedPreferences(Context context, boolean iClickSkip) {
        SharedPreferences mShare = context.getSharedPreferences("CLICKSKIP", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mShare.edit();
        mEditor.putBoolean("CLICKSKIP", iClickSkip);
        mEditor.commit();

    }
}
