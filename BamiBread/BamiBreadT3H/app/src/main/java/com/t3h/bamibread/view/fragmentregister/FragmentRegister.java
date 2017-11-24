package com.t3h.bamibread.view.fragmentregister;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.bamibread.R;
import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.view.fragmentlogin.FragmentLogin;
import com.t3h.bamibread.view.fragmentparent.FragmentParent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public class FragmentRegister extends BaseFragment implements IRegister.RegisterView {
    @BindView(R.id.btnRegister)
    AppCompatButton mRegister;

    private static final String TAG = FragmentRegister.class.getName();
    private RegisterPresenter mPresenter;
    private boolean checkPassWord;

    private TextView txtGmail;
    private TextView txtPass,txtPasswordConfirm;
    private TextView mNameUser;
   private TextInputLayout textEmail;
    private TextInputLayout textPass;
    private ProgressDialog progressDialog;

    @Override
    public int getLayoutMain() {
        return R.layout.fragment_register_layout;
    }

    @Override
    public void initComponents() {
        txtGmail=getView().findViewById(R.id.edt_gmail);
        txtPass=getView().findViewById(R.id.edt_pass);
        txtPasswordConfirm=getView().findViewById(R.id.edt_pass_confirm);
        mNameUser=getView().findViewById(R.id.edt_name_user);

        mPresenter = new RegisterPresenter(FragmentRegister.this);


    }

    @Override
    public void addEvents() {
        txtGmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d(TAG, "onFocusChange: " + b);
                if (!b) {
                    String mMail=txtGmail.getText().toString().trim();
                     textEmail = (TextInputLayout) view.getParent().getParent();
                    textEmail.setErrorEnabled(true);

                    mPresenter.checkUserNameSer(mMail);


                }

            }
        });
        txtPass.setOnFocusChangeListener((view, b) -> {
            if(!b){
                String text=txtPass.getText().toString().trim();
                textPass = (TextInputLayout) view.getParent().getParent();
                mPresenter.checkPassWord(text);

            }

        });
//        txtPasswordConfirm.setOnFocusChangeListener((view, b) -> {
//            if(!b){
//                String text=txtPasswordConfirm.getText().toString().trim();
//               TextInputLayout textPass = (TextInputLayout) view.getParent().getParent();
//                if(!text.equals(txtPass.getText())){
//                    textPass.setErrorEnabled(true);
//                    textPass.setError("Mật khẩu không khớp");
//                }else {
//                    textPass.setErrorEnabled(false);
//                    textPass.setError("");
//                }
//
//            }
//        });

    }


    @OnClick(R.id.btnRegister)
    public void onViewClicked() {
        if(mNameUser.getText().toString().equals("")||txtGmail.getText().toString().equals("")||!checkPassWord||!txtPass.getText().toString().equals(txtPasswordConfirm.getText().toString())){
            Toast.makeText(getContext(),"Bạn cần điền đủ các thông tin và chính xác mật khẩu",Toast.LENGTH_LONG).show();
            return;
        }
        mPresenter.registerSer(txtGmail.getText().toString(), txtPass.getText().toString(), mNameUser.getText().toString());
    }

    @Override
    public void finishCheckUserSer(boolean checkUser) {
        Log.d(TAG, "finishCheckUserSer++: "+checkUser);
        if (checkUser) {
            textEmail.setError("Tài khoản đã tồn tại");
        } else {
            if(!txtGmail.getText().toString().equals("")){
                textEmail.setError("Bạn có thể sử dụng tài khoản này");
            }else {
                textEmail.setErrorEnabled(false);
                textEmail.setError("");
            }


        }

    }

    @Override
    public void errorGetUserSer(Throwable error) {

    }


    @Override
    public void finshRegister(boolean checkRegister) {
        if (checkRegister) {
            Toast.makeText(getContext(),"Đăng Ký Thành Công",Toast.LENGTH_LONG).show();
            Bundle mBundel = new Bundle();
            mBundel.putString("NAME", txtGmail.getText().toString());
            mPresenter.saveRegister(getContext(),true,txtGmail.getText().toString());
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            BaseFragment.removeFragment(getActivity().getSupportFragmentManager(), transaction, FragmentRegister.class.getName(), ScreenAnimation.NONE, false, false);
            BaseFragment.openFragment(getActivity().getSupportFragmentManager(), transaction, FragmentParent.class, ScreenAnimation.NONE, mBundel, R.id.ln_activity, false, true);
        }else {
            Toast.makeText(getContext(),"Đăng ký thất bại bạn cần kiểm tra internet hoặc thông tin",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void errorRegister(Throwable error) {
        Log.d(TAG, "errorRegister: "+error);

    }

    @Override
    public void checkUserReg(boolean checkUser) {

    }

    @Override
    public void checkPassWord(boolean checkPass) {
        checkPassWord=checkPass;
        if(!checkPass){
            textPass.setErrorEnabled(true);
            textPass.setError("Mật khẩu phải có chữ hoa,số và >6 kí tự");
        }else {
            textPass.setErrorEnabled(false);
            textPass.setError("");
        }

    }
}
