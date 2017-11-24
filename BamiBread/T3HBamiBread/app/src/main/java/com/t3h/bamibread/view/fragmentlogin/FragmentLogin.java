package com.t3h.bamibread.view.fragmentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.t3h.bamibread.R;
import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.view.fragmentloginbyphone.FragmentLoginByPhone;
import com.t3h.bamibread.view.fragmentparent.FragmentParent;
import com.t3h.bamibread.view.fragmentregister.FragmentRegister;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public class FragmentLogin extends BaseFragment implements FacebookCallback<LoginResult>, ILogin.ViewLogin {



    @BindView(R.id.btnLogin)
    AppCompatButton mLogin;
    @BindView(R.id.create_one_user)
    TextView createOneUser;
    @BindView(R.id.btnLoginFacebook)
    LoginButton mLoginFacebook;
    @BindView(R.id.login_sms)
    TextView mLoginSMS;

    @BindView(R.id. edt_email)
    EditText edtMail;
    @BindView(R.id.edt_pasword)
    EditText edtPass;


    private CallbackManager mCallbackManager;
    private AccessTokenTracker mAccessTokenTracker;
    private ProfileTracker mProfileTracker;
    private static final String TAG = FragmentLogin.class.getSimpleName();

    private LoginPresenter mLoginPresenter;
    private Bundle mBundle;

    @Override
    public int getLayoutMain() {
        return R.layout.login_layout;
    }

    @Override
    public void initComponents() {
        mCallbackManager = CallbackManager.Factory.create();
        mLoginPresenter = new LoginPresenter(this);
        mBundle = new Bundle();

    }

    @Override
    public void addEvents() {
        LoginManager.getInstance().registerCallback(mCallbackManager, this);

        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.d(TAG, "onCurrentAccessTokenChanged: " + currentAccessToken);

            }
        };
        mAccessTokenTracker.startTracking();

        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d(TAG, "onCurrentProfileChanged: " + currentProfile);
                mLoginPresenter.getNameAndUrlImageFacebook(currentProfile, mBundle);

            }
        };


    }


    @Override
    public void onSuccess(LoginResult loginResult) {
        mLoginPresenter.getNameAndUrlImageFacebook(Profile.getCurrentProfile(), mBundle);

        //        GraphRequest request = GraphRequest.newMeRequest(
//                loginResult.getAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        // Insert your code here
//                        try {
//                            Log.d(TAG, "onSuccess: " + object.getString("name") + "====" + object.getString("email"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,email,birthday");
//        request.setParameters(parameters);
//        request.executeAsync();



    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLoginPresenter.getNameAndUrlImageFacebook(Profile.getCurrentProfile(), mBundle);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAccessTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    @Override
    public void openNextFragment(Bundle mBundle) {
        BaseFragment.hideFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentLogin.class, ScreenAnimation.NONE, false, true);
        BaseFragment.openFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentParent.class, ScreenAnimation.NONE, mBundle, R.id.ln_activity, false, true);

    }

    @Override
    public void checkLogin(boolean check) {
        if(check){
            Log.d(TAG, "checkLogin:======================== dang nhap thanh cong open");
        }
    }


    @OnClick({R.id.btnLogin, R.id.create_one_user, R.id.btnLoginFacebook,R.id.login_sms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                mLoginPresenter.checkLoginSer(edtMail.getText().toString(),edtPass.getText().toString());
                break;
            case R.id.create_one_user:
                BaseFragment.hideFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentLogin.class, ScreenAnimation.NONE, false, true);
                BaseFragment.openFragment(getFragmentManager(),
                        getFragmentManager().beginTransaction(), FragmentRegister.class, ScreenAnimation.NONE, mBundle, R.id.ln_activity, false, true);
                break;
            case R.id.btnLoginFacebook:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
                mLoginPresenter.getNameAndUrlImageFacebook(Profile.getCurrentProfile(), mBundle);
                break;
            case R.id.login_sms:
                BaseFragment.hideFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentLogin.class, ScreenAnimation.NONE, false, true);
                BaseFragment.openFragment(getFragmentManager(),
                        getFragmentManager().beginTransaction(), FragmentLoginByPhone.class, ScreenAnimation.NONE, mBundle, R.id.ln_activity, false, true);
                break;
        }
    }
}
