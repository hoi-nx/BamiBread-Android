package com.t3h.bamibread.view.fragmentloginbyphone;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.t3h.bamibread.R;
import com.t3h.bamibread.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class FragmentLoginByPhone extends BaseFragment {
    @BindView(R.id.img_icon_login)
    CircleImageView imgIconLogin;
    @BindView(R.id.txt_view_phone)
    TextView txtViewPhone;
    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.edt_phone_number)
    EditText edtPhoneNumber;
    @BindView(R.id.btn_send_code)
    Button btnSendCode;
    @BindView(R.id.txt_verified)
    TextView txtVerified;
    @BindView(R.id.edt_otp)
    EditText edtOtp;
    @BindView(R.id.btn_otp_verify)
    Button btnOtpVerify;



    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    @Override
    public int getLayoutMain() {
        return R.layout.fragment_login_by_phone;
    }

    @Override
    public void initComponents() {
        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Toast.makeText(getContext(),"Verification Complete", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getContext(),"Verification Failed", Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    Toast.makeText(getContext(),"InValid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                }

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {


                Toast.makeText(getContext(),"Verification code has been send on your number", Toast.LENGTH_SHORT).show();

                mVerificationId = verificationId;
                mResendToken = token;

                txtViewPhone.setVisibility(View.GONE);
                imgPhone.setVisibility(View.GONE);
                edtPhoneNumber.setVisibility(View.GONE);
                btnSendCode.setVisibility(View.GONE);

                txtVerified.setVisibility(View.VISIBLE);
                edtOtp.setVisibility(View.VISIBLE);
                btnOtpVerify.setVisibility(View.VISIBLE);

            }
        };

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    Toast.makeText(getContext(),"Verification Done", Toast.LENGTH_SHORT).show();
                    //done mở fragment trang chủ

                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                        Toast.makeText(getContext(),"Invalid Verification", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void addEvents() {

    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60, TimeUnit.SECONDS, getActivity(), mCallbacks, token);
    }


    @OnClick({R.id.btn_send_code, R.id.btn_otp_verify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_code:
                Toast.makeText(getContext(),"Send get Code", Toast.LENGTH_SHORT).show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+84"+edtPhoneNumber.getText().toString(), 60, TimeUnit.SECONDS, getActivity(), mCallbacks);

                break;
            case R.id.btn_otp_verify:
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, edtOtp.getText().toString());
                signInWithPhoneAuthCredential(credential);
                break;
        }
    }
}
