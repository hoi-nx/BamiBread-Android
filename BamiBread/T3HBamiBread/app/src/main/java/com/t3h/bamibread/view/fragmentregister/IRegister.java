package com.t3h.bamibread.view.fragmentregister;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public interface IRegister {
    interface RegisterView{
        void finishCheckUser(boolean b);
        void errorGetUser(Throwable error);

        void finshRegister(boolean checkRegister);
        void errorRegister(Throwable error);


    }
    interface PresenterRegister{
        void checkUserSer(String text);
        boolean checkUser(String text);
        void checkRegisterSer(String name,String username,String pass);
    }
}
