package com.t3h.bamibread;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.util.Log;
import android.widget.LinearLayout;

import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseActivity;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.view.fragmentlogin.FragmentLogin;
import com.t3h.bamibread.view.fragmentsplashscreen.FragmentSplashScreen;
import com.t3h.bamibread.view.fragmentuseguide.FragmentUseGuide;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private Handler mHandler;


    @BindView(R.id.ln_activity)
    LinearLayout mLinearAcitivy;

    @Override
    public int getLayoutMain() {
        return R.layout.activity_main;
    }


    @Override
    public void initComponents() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment.openFragment(fragmentManager, fragmentManager.beginTransaction(), FragmentSplashScreen.class, ScreenAnimation.NONE, null, R.id.ln_activity, false, true);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BaseFragment.removeFragment(fragmentManager, fragmentManager.beginTransaction(), FragmentSplashScreen.class.getName(), ScreenAnimation.NONE, false, true);
                if (getSharedPreferences()) {
                    BaseFragment.openFragment(fragmentManager, fragmentManager.beginTransaction(), FragmentLogin.class, ScreenAnimation.OPEN_FULL, null, R.id.ln_activity, false, true);
                    return;
                }
                BaseFragment.openFragment(fragmentManager, fragmentManager.beginTransaction(), FragmentUseGuide.class, ScreenAnimation.NONE, null, R.id.ln_activity, false, true);
            }
        }, 2000);

    }

    @Override
    public void addEvents() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.delaroystudios.facebookloginuserdetails",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

    }

    public boolean getSharedPreferences() {
        SharedPreferences mShare = getSharedPreferences("CLICKSKIP", Context.MODE_PRIVATE);
        return mShare.getBoolean("CLICKSKIP", false);
    }

}
