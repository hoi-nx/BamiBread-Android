package com.t3h.bamibread.view.fragmentchild.fragmenthomepage;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.bamibread.R;
import com.t3h.bamibread.ShoppingCartActivity;
import com.t3h.bamibread.adapters.AdapterTopHomePage;
import com.t3h.bamibread.adapters.AdapterViewPagerFoodHomePage;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.interfaces.IList;
import com.t3h.bamibread.interfaces.IListFastFood;
import com.t3h.bamibread.interfaces.IListNew;
import com.t3h.bamibread.model.FastFood;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import io.realm.Realm;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class FragmentHomePage extends BaseFragment implements IHomePage.HomePageView, IListFastFood, IList, IListNew {
    private final static String TAG = FragmentHomePage.class.getName();
    @BindView(R.id.cir_viewpager_food)
    CircleIndicator mCircle;
    @BindView(R.id.view_pager_food)
    ViewPager mViewPagerFood;
    @BindView(R.id.view_pager_top)
    ViewPager mViewPagerTop;
    @BindView(R.id.cir_top)
    CircleIndicator mCircleTop;
    private HomePagePresenter mHomePagePresenter;
    private List<String> listUrlImg;

    private AdapterViewPagerFoodHomePage mAdapterViewPagerFoodHomePage;
    private AdapterTopHomePage mAdapterTop;
    private List<FastFood> fastFoods;

    private TimerTask mTimeTask;
    private Timer mTimer;
    private List<String> listImageSecond;
    private Realm mRealm;
    private TextView txtNumberOfShoopingCart;
    private boolean isPause;

    @Override
    public int getLayoutMain() {
        return R.layout.fragment_home_page_layout;
    }

    @Override
    public void initComponents() {
        Realm.init(getContext());
        mRealm = Realm.getDefaultInstance();
        setHasOptionsMenu(true);
        mHomePagePresenter = new HomePagePresenter(this);
        mHomePagePresenter.getListIntroduce();
        // mHomePagePresenter.getListImage();
        mHomePagePresenter.getListFastFood();


    }


    @Override
    public void addEvents() {


    }

    @Override
    public void finishGetListImg(List<String> listImg) {
        if (listImg.size() > 0) {
            Log.d(TAG, "finishGetListImg: " + listImg.size());
            listUrlImg = listImg;
            mAdapterTop = new AdapterTopHomePage(this);
            mViewPagerTop.setAdapter(mAdapterTop);
            mCircleTop.setViewPager(mViewPagerTop);
            mAdapterTop.notifyDataSetChanged();
            animationViewPager();

        }

    }

    private void animationViewPager() {
        mTimeTask = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getView() == null) {
                            return;
                        }
                        int index = mViewPagerTop.getCurrentItem() + 1 % mAdapterTop.getCount();
                        mViewPagerTop.setCurrentItem(index);
                        Log.d(TAG, "run: " + index);
                        if (index > listUrlImg.size() - 1) {
                            index = 0;
                            mViewPagerTop.setCurrentItem(index);
                        }
                    }
                });
            }
        };
        mTimer = new Timer();
        mTimer.schedule(mTimeTask, 0, 3000);
    }

    @Override
    public void errorGetString(Throwable error) {
        Log.d(TAG, "errorGetString: " + error);

    }

    @Override
    public void finishGetListFastFood(List<FastFood> listFastFood) {
        Log.d(TAG, "finishGetListFastFood: " + listFastFood.size());
        if (listFastFood.size() > 0) {
            fastFoods = listFastFood;
            mAdapterViewPagerFoodHomePage = new AdapterViewPagerFoodHomePage(this, getActivity());
            mViewPagerFood.setAdapter(mAdapterViewPagerFoodHomePage);
            mCircle.setViewPager(mViewPagerFood);
            mAdapterViewPagerFoodHomePage.notifyDataSetChanged();
        }

    }

    @Override
    public void errorGetFF(Throwable error) {
        Log.d(TAG, "errorGetFF: " + error);

    }


    @Override
    public int countList() {
        return fastFoods == null ? 0 : fastFoods.size();
    }

    @Override
    public FastFood getDataFF(int position) {
        return fastFoods.get(position);
    }

    @Override
    public Object getImageSecond(int positon) {
        return listImageSecond.get(positon);
    }

    @Override
    public void clickOrderFood(int position) {
        FastFood fastFood = fastFoods.get(position);
        if (mHomePagePresenter.saveOrderFood(mRealm, fastFood.getName(), fastFood.getPrice(), 1, fastFood.getUrlImage())) {
            Toast.makeText(getContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_LONG).show();
            txtNumberOfShoopingCart.setText(mHomePagePresenter.getListOrderFood(mRealm).size() + "");
        } else {
            Toast.makeText(getContext(), "Đã có trong giỏ hàng", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public int count() {
        return listUrlImg == null ? 0 : listUrlImg.size();
    }

    @Override
    public Object getData(int position) {
        return listUrlImg.get(position);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home_page_shopping_cart, menu);
        MenuItem menuItem = menu.findItem(R.id.shopping_cart);
        View shoppingCart = MenuItemCompat.getActionView(menuItem);
        shoppingCart.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ShoppingCartActivity.class);
            startActivity(intent);

        });
        txtNumberOfShoopingCart = shoppingCart.findViewById(R.id.txtNumberOfShoppingCart);
        txtNumberOfShoopingCart.setText(mHomePagePresenter.getListOrderFood(mRealm).size() + "");

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onClickItem(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPause) {
            animationViewPager();
            txtNumberOfShoopingCart.setText(mHomePagePresenter.getListOrderFood(mRealm).size() + "");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isPause = true;
        if (mTimeTask != null && mTimer != null) {
            mTimeTask.cancel();
            mTimer.cancel();
        }
    }

}
