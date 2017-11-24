package com.t3h.bamibread.view.fragmentchild.fragmentliststore;

import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.managers.ManagerListStore;
import com.t3h.bamibread.model.StoreBamiBread;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class ListStorePresenter extends BasePresenter<IListStore.ListStoreView>implements IListStore.PresenterListStore {
    public ListStorePresenter(IListStore.ListStoreView view) {
        super(view);
    }



    @Override
    public void getListStoreBami() {
        interact(ManagerListStore.getIntance().getListStoreBami(), new Action<List<StoreBamiBread>>() {
            @Override
            public void call(List<StoreBamiBread> storeBamiBreads) {
                getMyView().finishgetListStore(storeBamiBreads);

            }
        }, new Action<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMyView().errorgetListStore(throwable);

            }
        });
    }
}
