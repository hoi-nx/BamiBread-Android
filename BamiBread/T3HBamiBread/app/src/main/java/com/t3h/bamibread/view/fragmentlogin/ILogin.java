package com.t3h.bamibread.view.fragmentlogin;

import android.os.Bundle;

import com.facebook.Profile;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public interface ILogin {
    interface ViewLogin{

        void openNextFragment(Bundle mBundle);
        void checkLogin(boolean check);
    }
    interface PresenterLogin{
        boolean getNameAndUrlImageFacebook(Profile mProfile,Bundle mBundle);
        void checkLoginSer(String username,String password);

    }
}
