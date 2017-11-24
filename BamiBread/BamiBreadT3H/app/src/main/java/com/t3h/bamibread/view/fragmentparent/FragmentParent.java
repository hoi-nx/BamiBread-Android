package com.t3h.bamibread.view.fragmentparent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.customview.CustomPopup;
import com.t3h.bamibread.customview.PopupHotline;
import com.t3h.bamibread.view.fragmentchild.fragmenthomepage.FragmentHomePage;
import com.t3h.bamibread.view.fragmentchild.fragmentliststore.FragmentListStore;
import com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment.FragmentParentNewsAndRecruit;
import com.t3h.bamibread.view.fragmentlogin.FragmentLogin;

import java.io.InputStream;

import butterknife.BindView;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public class FragmentParent extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.my_tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;



    private TextView mTxtName;
    private ImageView mImageUser;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private static final String TAG = FragmentParent.class.getSimpleName();
    private ParentPresenter parentPresenter;




    @Override
    public int getLayoutMain() {
        return R.layout.fragment_parent_home;
    }

    @Override
    public void initComponents() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.header_draw_navigation, mNavigationView, false);
        mNavigationView.addHeaderView(headerView);
        mImageUser = headerView.findViewById(R.id.circle_img_facebook);
        mTxtName = headerView.findViewById(R.id.txt_ten);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBar);
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        BaseFragment.openFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), FragmentHomePage.class, ScreenAnimation.OPEN_FULL, null, R.id.frag_home_page, false, true);


        Bundle bundle = getArguments();
        if (bundle != null) {
            String urlImg = bundle.getString("URLIMG");
            if (urlImg != null) {
                Picasso.with(getContext()).load(urlImg.toString()).error(R.drawable.icon_app).placeholder(R.drawable.icon_app).into(mImageUser);
            }
            String name = bundle.getString("NAME");
            if (name != null) {
                mTxtName.setText(name);
            }


        }
        //Intent intent=new Intent();
//            intent.setAction(Intent.ACTION_PICK);
//            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            intent.setType("image/*");
//            startActivityForResult(intent,100);


    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode== Activity.RESULT_CANCELED){
//            return;
//        }
//        if(requestCode==100){
//            Uri uri= data.getData();
//
//            //Bitmap là đối tượng gần giống Image trong java dùng để biểu thị 1 hình ảnh
//            //uri---->Inputstream----->BitMap---->IV
//            try {
//                InputStream inputStream=getContext().getContentResolver().openInputStream(uri);
//                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
//                inputStream.close();
//                mImageUser.setImageBitmap(bitmap);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    @Override
    public void addEvents() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        mToolBar.setNavigationOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homepage:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mActionBar.setTitle(item.getTitle());
                hideCurrentFragment(FragmentHomePage.class.getName());
                BaseFragment.openFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), FragmentHomePage.class, ScreenAnimation.OPEN_FULL, null, R.id.frag_home_page, false, true);
                break;
            case R.id.new_and_recruit:
                mActionBar.setTitle(item.getTitle());
                mDrawerLayout.closeDrawer(GravityCompat.START);
                hideCurrentFragment(FragmentParentNewsAndRecruit.class.getName());
                BaseFragment.openFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), FragmentParentNewsAndRecruit.class, ScreenAnimation.OPEN_FULL, null, R.id.frag_home_page, false, true);
                break;
            case R.id.my_message:
                break;
            case R.id.call_hotline:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                PopupHotline popupHotline=new PopupHotline(getContext());
                popupHotline.show();
                break;
            case R.id.setting_app:
                break;
            case R.id.exitUser:
                LoginManager.getInstance().logOut();
                BaseFragment.removeFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentParent.class.getName(), ScreenAnimation.NONE, false, true);
                BaseFragment.openFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentLogin.class, ScreenAnimation.OPEN_FULL, null, R.id.ln_activity, false, true);
                parentPresenter=new ParentPresenter();
                parentPresenter.saveLogOut(getContext(),false);
                break;
            case R.id.bamibread:
                mActionBar.setTitle(item.getTitle());
                mDrawerLayout.closeDrawer(GravityCompat.START);
                hideCurrentFragment(FragmentListStore.class.getName());
                BaseFragment.openFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), FragmentListStore.class, ScreenAnimation.OPEN_FULL, null, R.id.frag_home_page, false, true);
                break;

        }

        return false;
    }

    private void hideCurrentFragment(String tag) {
        Fragment fragment = getCurrentBaseFragment(getChildFragmentManager());
        if (fragment != null && fragment.getTag() != tag) {
            Log.d(TAG, "onNavigationItemSelected: " + fragment.getTag());
            // BaseFragment.hideFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), (Class<? extends BaseFragment>) fragment.getClass(), ScreenAnimation.NONE, false, true);
            BaseFragment.removeFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), fragment.getTag(), ScreenAnimation.NONE, false, true);
        }
    }



}
