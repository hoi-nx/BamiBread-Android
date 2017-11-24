package com.t3h.bamibread.managers;

import com.t3h.bamibread.model.OrderFood;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Heart Of Dead on 9/20/2017.
 */

public class ManagerRealm {
    private static ManagerRealm managerRealm = new ManagerRealm();

    public static ManagerRealm getIntance() {
        return managerRealm;
    }

    public RealmResults<OrderFood> getListOrderFood(Realm realm) {
        RealmResults<OrderFood> results = realm.where(OrderFood.class).findAll();
        return results;
    }

    public boolean updateOrderFood(Realm realm, OrderFood orderFood, int number, int totalPrice) {
        OrderFood food = realm.where(OrderFood.class).equalTo("name", orderFood.getName()).findFirst();
        if (food != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    orderFood.setNumber(number);
                    orderFood.setTotalPrice(totalPrice);
                }
            });
            return true;
        }
        return false;
    }

    public boolean deleteOrderFood(Realm realm, OrderFood orderFood) {
        OrderFood food = realm.where(OrderFood.class).equalTo("name", orderFood.getName()).findFirst();
        if (food != null) {
            realm.beginTransaction();
            food.deleteFromRealm();
            realm.commitTransaction();
            return true;
        }
        return false;
    }

    public boolean saveOrderFood(Realm realm, String name, int price, int number, String urlImage) {
        OrderFood orderFood = realm.where(OrderFood.class).equalTo("name", name).findFirst();
        if (orderFood != null) {
            return false;
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                OrderFood orderFood = new OrderFood(name, price, number, (price * number), urlImage);
                realm.copyToRealm(orderFood);

            }
        });
        return true;
    }

    public int totalPriceListOrder(RealmResults<OrderFood> realmResults) {
        int total = 0;
        for (OrderFood orderFood : realmResults) {
            total += orderFood.getTotalPrice();
        }
        return total;
    }

    public boolean clearAllListOrder(Realm mRealm) {
        RealmResults<OrderFood> results = mRealm.where(OrderFood.class).findAll();
        if(results==null){
            return false;
        }
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();

            }

        });
        return true;
    }

}
