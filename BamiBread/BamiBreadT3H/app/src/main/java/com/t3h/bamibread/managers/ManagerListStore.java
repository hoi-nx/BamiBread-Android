package com.t3h.bamibread.managers;

import com.t3h.bamibread.R;
import com.t3h.bamibread.interfaces.Contants;
import com.t3h.bamibread.model.FastFood;
import com.t3h.bamibread.model.StoreBamiBread;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class ManagerListStore {
    private static ManagerListStore managerListStore=new ManagerListStore();
    public static ManagerListStore getIntance(){
        return managerListStore;
    }


    public Observable<List<StoreBamiBread>> getListStoreBami() {
        return Observable.create(new ObservableOnSubscribe<List<StoreBamiBread>>() {
            @Override
            public void subscribe(ObservableEmitter<List<StoreBamiBread>> e) throws Exception {
                List<StoreBamiBread> listStoreBami = new ArrayList<StoreBamiBread>();
                String link = Contants.SEVER_NAME+"storebami";
                try {
                    JSONArray jsonArray=new JSONArray(Utils.getLinkSer(link));
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String nameStore=jsonObject.getString("NameStore");
                        String phoneStore=jsonObject.getString("PhoneStore");
                        String linkImg=jsonObject.getString("LinkImage");
                        StoreBamiBread storeBamiBread=new StoreBamiBread(nameStore,phoneStore,linkImg);
                        listStoreBami.add(storeBamiBread);
                    }
                } catch (Exception ex) {
                }
                e.onNext(listStoreBami);
            }
        });
    }
}
