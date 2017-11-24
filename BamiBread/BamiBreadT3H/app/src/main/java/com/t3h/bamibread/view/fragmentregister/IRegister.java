package com.t3h.bamibread.view.fragmentregister;

import android.content.Context;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public interface IRegister {
    interface RegisterView{
        void finishCheckUserSer(boolean b);
        void errorGetUserSer(Throwable error);

        void finshRegister(boolean checkRegister);
        void errorRegister(Throwable error);
        void checkUserReg(boolean checkUser);

        void checkPassWord(boolean checkPass);
    }
    interface PresenterRegister{
        public void checkUserNameSer(String username);
        public void registerSer(String username,String password,String name);
        void checkUser(String text);
        void checkPassWord(String string);
        void saveRegister(Context context, boolean iClickLogin, String email);

    }
}
