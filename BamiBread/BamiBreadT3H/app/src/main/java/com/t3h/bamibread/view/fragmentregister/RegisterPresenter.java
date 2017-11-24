package com.t3h.bamibread.view.fragmentregister;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;

import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.managers.ManagerRegister;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public class RegisterPresenter extends BasePresenter<IRegister.RegisterView> implements IRegister.PresenterRegister {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASS_WORD="((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";

    public RegisterPresenter(IRegister.RegisterView view) {
        super(view);
    }

    @Override
    public void checkUserNameSer(String username) {
        interact(ManagerRegister.getIntance().checkUserOb(username), new Action<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                Log.d("", "call===========: "+aBoolean);
                getMyView().finishCheckUserSer(aBoolean);

            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMyView().errorGetUserSer(throwable);
            }
        });

    }

    @Override
    public void registerSer(String username, String password, String name) {
        interact(ManagerRegister.getIntance().registerOb(name, username, password), new Action<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                getMyView().finshRegister(aBoolean);
            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMyView().errorRegister(throwable);
            }
        });

    }

    @Override
    public void checkUser(String text) {
        Pattern pattern=Pattern.compile(EMAIL_PATTERN);
        Matcher matcher=pattern.matcher(text);
        getMyView().checkUserReg(matcher.matches());


    }

    @Override
    public void checkPassWord(String string) {
        Pattern pattern=Pattern.compile(PASS_WORD);
        Matcher matcher=pattern.matcher(string);
        getMyView().checkPassWord(matcher.matches());
    }

    @Override
    public void saveRegister(Context context, boolean iClickLogin, String email) {
        SharedPreferences mShare = context.getSharedPreferences("LOGINCLICK", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mShare.edit();
        mEditor.putBoolean("LOGINCLICK", iClickLogin);
        mEditor.putString("EMAILS",email);
        mEditor.commit();
    }


}
