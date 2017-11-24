package com.t3h.bamibread.view.fragmentlogin;

import android.content.Context;
import android.os.Bundle;

import com.facebook.Profile;
import com.t3h.bamibread.base.Presenter;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public interface ILogin {
    interface ViewLogin{

        void openNextFragment(Bundle mBundle);
        void checkLogin(boolean checkLogin);
    }
    interface PresenterLogin {
        boolean getNameAndUrlImageFacebook(Profile mProfile, Bundle mBundle);
        void checkLoginSer(String username, String password);
        void saveLogin(Context context,boolean iclickLogin,String email);
        boolean checkInterNet();

    }
}
