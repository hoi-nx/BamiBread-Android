package com.t3h.bamibread.view.fragmentparent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.CustomPopupCallHotline;
import com.t3h.bamibread.animation.ScreenAnimation;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.view.fragmentchild.fragmenthomepage.FragmentHomePage;
import com.t3h.bamibread.view.fragmentchild.fragmentintroducestore.FragmentIntroduceStore;
import com.t3h.bamibread.view.fragmentchild.fragmentstore.FragmentListStore;
import com.t3h.bamibread.view.fragmentlogin.FragmentLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

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


    @Override
    public int getLayoutMain() {
        return R.layout.fragment_parent_home;
    }

    @Override
    public void initComponents() {
        setHasOptionsMenu(true);//hiện cuwsstom menu
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.header_draw_navigation, mNavigationView, false);
        mNavigationView.addHeaderView(headerView);
        mImageUser = headerView.findViewById(R.id.circle_img_facebook);
        mTxtName = headerView.findViewById(R.id.txt_ten);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBar);
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String urlImg = bundle.getString("URLIMG").toString();
            String name = bundle.getString("NAME");
            mTxtName.setText(name);
            Picasso.with(getContext()).load(urlImg).error(R.drawable.icon_app).placeholder(R.drawable.icon_app).into(mImageUser);
        }


        BaseFragment.openFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), FragmentHomePage.class, ScreenAnimation.OPEN_FULL, null, R.id.frame_layout_child, false, true);

    }

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
        //phai kiem tra fragment day da dc mo hay chua nếu rồi thì thôi......(người dùng kích nhiều lần)
        //khi chuyển sang fragment khác phải lấy được cái fragment đang hiên thị(xóa hoặc ẩn nó đi) rồi mở fragment mới
        //kiểm tra cả null nữa

        switch (item.getItemId()) {
            case R.id.homepage:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                removeCurrentFragment(FragmentHomePage.class.getName());
                BaseFragment.openFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), FragmentHomePage.class, ScreenAnimation.OPEN_FULL, null, R.id.frame_layout_child, false, true);
                break;
            case R.id.introduce:
                removeCurrentFragment(FragmentIntroduceStore.class.getName());
                BaseFragment.openFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), FragmentIntroduceStore.class, ScreenAnimation.OPEN_FULL, null, R.id.frame_layout_child, false, true);
                break;
            case R.id.recruit:
                break;
            case R.id.my_message:
                break;
            case R.id.call_hotline:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                CustomPopupCallHotline customPopupCallHotline=new CustomPopupCallHotline(getContext());
                customPopupCallHotline.show();
                break;
            case R.id.setting_app:
                break;
            case R.id.exitUser:
                LoginManager.getInstance().logOut();
                BaseFragment.removeFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentParent.class.getName(), ScreenAnimation.NONE, false, true);
                BaseFragment.openFragment(getFragmentManager(), getFragmentManager().beginTransaction(), FragmentLogin.class, ScreenAnimation.OPEN_FULL, null, R.id.ln_activity, false, true);
                break;
            case R.id.bamibread:
                Fragment fragment=BaseFragment.getCurrentBaseFragment(getFragmentManager());
                Log.d(TAG, "onNavigationItemSelected: "+fragment.getTag());

                Fragment fragment2=BaseFragment.getCurrentBaseFragment(getChildFragmentManager());
                if(fragment2!=null){
                    Log.d(TAG, "onNavigationItemSelected======: "+fragment2.getTag());
                }
                removeCurrentFragment(FragmentListStore.class.getName());
                BaseFragment.openFragment(getChildFragmentManager(),getChildFragmentManager().beginTransaction(),FragmentListStore.class,ScreenAnimation.NONE,null,R.id.frame_layout_child,false,true);

//


                break;

        }

        return false;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home_page_shopping_cart, menu);
        MenuItem menuItem = menu.findItem(R.id.shopping_cart);
        View shoppingCart = MenuItemCompat.getActionView(menuItem);

        super.onCreateOptionsMenu(menu, inflater);
    }
}
