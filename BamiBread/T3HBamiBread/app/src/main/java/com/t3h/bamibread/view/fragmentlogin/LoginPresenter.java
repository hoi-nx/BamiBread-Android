package com.t3h.bamibread.view.fragmentlogin;

import android.os.Bundle;

import com.facebook.Profile;
import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.base.Presenter;
import com.t3h.bamibread.managers.ManagerLogin;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public class LoginPresenter extends BasePresenter<ILogin.ViewLogin> implements ILogin.PresenterLogin {
    public LoginPresenter(ILogin.ViewLogin view) {
        super(view);
    }

    @Override
    public boolean getNameAndUrlImageFacebook(Profile mProfile, Bundle mBundle) {
        if (mProfile != null) {
            mBundle.putString("URLIMG", mProfile.getProfilePictureUri(200, 200).toString());
            mBundle.putString("NAME", mProfile.getName());
            getMyView().openNextFragment(mBundle);

        }
        return false;
    }

    @Override
    public void checkLoginSer(String username, String password) {
        interact(ManagerLogin.getInstance().checkLoginSer(username, password), new Action<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                getMyView().checkLogin(aBoolean);

            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });

    }
}
