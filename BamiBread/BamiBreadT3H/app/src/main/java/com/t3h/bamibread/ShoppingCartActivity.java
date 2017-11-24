package com.t3h.bamibread;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t3h.bamibread.adapters.AdapterRcListOrder;
import com.t3h.bamibread.base.BaseActivity;
import com.t3h.bamibread.customview.CustomPopup;
import com.t3h.bamibread.interfaces.IListOrder;
import com.t3h.bamibread.interfaces.IPopup;
import com.t3h.bamibread.managers.ManagerRealm;
import com.t3h.bamibread.model.FastFood;
import com.t3h.bamibread.model.OrderFood;
import com.t3h.bamibread.model.Request;
import com.t3h.bamibread.view.fragmentchild.fragmenthomepage.HomePagePresenter;
import com.t3h.bamibread.view.fragmentchild.fragmenthomepage.IHomePage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class ShoppingCartActivity extends BaseActivity implements IListOrder, IPopup {

    @BindView(R.id.toolbar_)
    Toolbar mToolbar;
    @BindView(R.id.rc_list_order)
    RecyclerView rcListOrder;
    @BindView(R.id.btnOrder)
    AppCompatButton btnOrder;
    @BindView(R.id.total_price_list_order)
    TextView mTotalListOrder;

    private AdapterRcListOrder mAdapterRcListOrder;
    private Realm mRealm;
    private RealmResults<OrderFood> results;



    @Override
    public int getLayoutMain() {
        return R.layout.shopping_cart_activity;
    }

    @Override
    public void initComponents() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopping Cart");
        mToolbar.setLogo(R.drawable.ic_action_shopping_cart);


        Realm.init(this);
        mRealm = Realm.getDefaultInstance();
        results = ManagerRealm.getIntance().getListOrderFood(mRealm);
        mAdapterRcListOrder = new AdapterRcListOrder(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcListOrder.setLayoutManager(layoutManager);
        rcListOrder.setAdapter(mAdapterRcListOrder);
        totalPriceListOrder();
    }

    private void totalPriceListOrder() {
        mTotalListOrder.setText("Total= "+ ManagerRealm.getIntance().totalPriceListOrder(results)+"đ");
    }

    @Override
    public void addEvents() {
        findViewById(R.id.btnOrder).setOnClickListener(view -> {
            Toast.makeText(this,"Đặt Hàng Click",Toast.LENGTH_LONG).show();
            CustomPopup customPopup = new CustomPopup(ShoppingCartActivity.this,this);
            customPopup.show();
        });

    }

    @Override
    public int count() {
        return results == null ? 0 : results.size();
    }

    @Override
    public OrderFood getOrder(int position) {
        return results.get(position);
    }

    @Override
    public void deleteOrderFood(int position) {
        OrderFood orderFood = results.get(position);
        if (ManagerRealm.getIntance().deleteOrderFood(mRealm, orderFood)) {
            Toast.makeText(this, "Đã Xóa Thành Công", Toast.LENGTH_LONG).show();
            totalPriceListOrder();
            mAdapterRcListOrder.notifyDataSetChanged();
            return;
        }
        Toast.makeText(this, "Xóa Thất Bại", Toast.LENGTH_LONG).show();
    }

    @Override
    public void countUpClick(int position) {
        OrderFood orderFood = results.get(position);
        int number = orderFood.getNumber();
        number++;
        int totalPrice = number * orderFood.getPrice();
        if (ManagerRealm.getIntance().updateOrderFood(mRealm, orderFood, number, totalPrice)) {
            totalPriceListOrder();
            mAdapterRcListOrder.notifyDataSetChanged();
            //Toast.makeText(this, "Thành Công", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void countDownClick(int position) {
        OrderFood orderFood = results.get(position);
        int number = orderFood.getNumber();
        if (number == 1) {
            return;
        }
        number--;
        int price = number * orderFood.getPrice();
        if (ManagerRealm.getIntance().updateOrderFood(mRealm, orderFood, number, price)) {
            totalPriceListOrder();
            mAdapterRcListOrder.notifyDataSetChanged();
            // Toast.makeText(this, "Thành Công", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void clearAllListOrder() {
        if(ManagerRealm.getIntance().clearAllListOrder(mRealm)){
            Toast.makeText(this,"Thanks for your order",Toast.LENGTH_LONG).show();
            totalPriceListOrder();
            mAdapterRcListOrder.notifyDataSetChanged();
        }
    }
}
