package com.t3h.bamibread.view.fragmentregister;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.t3h.bamibread.R;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.customview.PasswordEditText;

import butterknife.BindView;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public class FragmentRegister extends BaseFragment implements IRegister.RegisterView {

    @BindView(R.id.edt_name_user)
    TextInputEditText edtNameUser;
    @BindView(R.id.edt_gmail)
    TextInputEditText edtGmail;
    @BindView(R.id.edt_pass)
    PasswordEditText edtPass;
    @BindView(R.id.edt_pass_confirm)
    PasswordEditText edtPassConfirm;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    private PresenterRegister mRegister;
    private boolean check;
    private static final String TAG = FragmentRegister.class.getSimpleName();

    @Override
    public int getLayoutMain() {
        return R.layout.fragment_register_layout;
    }

    @Override
    public void initComponents() {
        mRegister = new PresenterRegister(this);

    }

    @Override
    public void addEvents() {
        edtGmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                Log.d(TAG, "onFocusChange:======== " + b);
                TextInputLayout text = (TextInputLayout) view.getParent().getParent();
                text.setErrorEnabled(true);
                if (b==false) {
                    mRegister.checkUserSer(edtGmail.getText().toString());
                    if (check) {
                        text.setError("Tài Khoản đã tồn tại");
                    } else {
                        text.setError("Bạn có thể sử dụng tài khoản này");
                    }
                }


            }
        });

        btnRegister.setOnClickListener(view -> {
            if (edtNameUser.getText().equals("") == false && check == false && edtPass.getText().equals("") == false && edtPass.getText().toString().equals(edtPassConfirm.getText().toString())) {
                mRegister.checkRegisterSer(edtNameUser.getText().toString(), edtGmail.getText().toString(), edtPass.getText().toString());
            }

        });

    }


    @Override
    public void finishCheckUser(boolean b) {
        check = b;
        Log.d(TAG, "finishCheckUser: " + check);

    }

    @Override
    public void errorGetUser(Throwable error) {
        Log.d(TAG, "errorGetUser: " + error);

    }

    @Override
    public void finshRegister(boolean checkRegister) {
        if (checkRegister) {
            Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
            //open fragparent hoặc quay lại fragment login
        }

    }

    @Override
    public void errorRegister(Throwable error) {
        Log.d(TAG, "errorRegister: " + error);

    }
}
