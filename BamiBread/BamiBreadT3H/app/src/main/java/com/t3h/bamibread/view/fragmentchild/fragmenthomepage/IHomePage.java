package com.t3h.bamibread.view.fragmentchild.fragmenthomepage;

import com.t3h.bamibread.model.FastFood;
import com.t3h.bamibread.model.OrderFood;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public interface IHomePage {
    interface HomePageView {
        void finishGetListImg(List<String> listImg);

        void errorGetString(Throwable error);

        void finishGetListFastFood(List<FastFood> listFastFood);

        void errorGetFF(Throwable error);
    }

    interface PresenterHomePage {
        void getListImage();

        void getListFastFood();

        void getListIntroduce();

        boolean saveOrderFood(Realm realm, String name, int price,int number, String urlImage);

        boolean deleteOrderFood(Realm realm, OrderFood orderFood);
        boolean updateOrderFood(Realm realm,OrderFood orderFood,int number,int price);

        RealmResults<OrderFood> getListOrderFood(Realm realm);

    }
}
