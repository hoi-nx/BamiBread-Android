package com.t3h.bamibread.view.fragmentregister;

import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.managers.ManagerRegister;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public class PresenterRegister extends BasePresenter<IRegister.RegisterView> implements IRegister.PresenterRegister {
    public PresenterRegister(IRegister.RegisterView view) {
        super(view);
    }

    @Override
    public void checkUserSer(String text) {
        interact(ManagerRegister.getIntance().checkUserOb(text), new Action<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                getMyView().finishCheckUser(aBoolean);
            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMyView().errorGetUser(throwable);

            }
        });

    }

    @Override
    public boolean checkUser(String text) {
        String reg="^[\\w_\\.]+\\@[\\w&&[^0-9]]+\\.[\\w&&[^0-9]]+$";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(text);
        if(matcher.matches()){
            return true;

        }
        return false;

    }

    @Override
    public void checkRegisterSer(String name, String username, String pass) {
        interact(ManagerRegister.getIntance().registerOb(name, username, pass), new Action<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                getMyView().finshRegister(aBoolean);

            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMyView().errorGetUser(throwable);
            }
        });

    }
}
