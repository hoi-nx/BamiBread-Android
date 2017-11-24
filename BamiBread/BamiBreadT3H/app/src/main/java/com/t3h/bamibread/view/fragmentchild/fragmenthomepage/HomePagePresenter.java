package com.t3h.bamibread.view.fragmentchild.fragmenthomepage;

import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.managers.ManagerHomePage;
import com.t3h.bamibread.managers.ManagerRealm;
import com.t3h.bamibread.model.FastFood;
import com.t3h.bamibread.model.OrderFood;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class HomePagePresenter extends BasePresenter<IHomePage.HomePageView> implements IHomePage.PresenterHomePage {
    public HomePagePresenter(IHomePage.HomePageView view) {
        super(view);
    }

    @Override
    public void getListImage() {
        interact(ManagerHomePage.getIntance().getListImg(), new Action<List<String>>() {
            @Override
            public void call(List<String> strings) {
                getMyView().finishGetListImg(strings);

            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    @Override
    public void getListFastFood() {
        interact(ManagerHomePage.getIntance().getListFastFood(), new Action<List<FastFood>>() {
            @Override
            public void call(List<FastFood> list) {
                getMyView().finishGetListFastFood(list);

            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMyView().errorGetFF(throwable);
            }
        });

    }

    @Override
    public void getListIntroduce() {
        interact(ManagerHomePage.getIntance().getListIntroduce(), new Action<List<String>>() {
            @Override
            public void call(List<String> strings) {
                getMyView().finishGetListImg(strings);
            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });

    }

    @Override
    public boolean saveOrderFood(Realm realm, String name, int price,int number, String urlImage) {
       if(ManagerRealm.getIntance().saveOrderFood(realm,name,price,number,urlImage)){
           return true;

       }
       return false;
    }

    @Override
    public boolean deleteOrderFood(Realm realm, OrderFood orderFood) {
        if(ManagerRealm.getIntance().deleteOrderFood(realm,orderFood)){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrderFood(Realm realm, OrderFood orderFood,int number,int totalPrice) {
        if(ManagerRealm.getIntance().updateOrderFood(realm,orderFood,number,totalPrice)){
            return true;
        }
        return false;
    }

    @Override
    public RealmResults<OrderFood> getListOrderFood(Realm realm) {
        return ManagerRealm.getIntance().getListOrderFood(realm);
    }

}
