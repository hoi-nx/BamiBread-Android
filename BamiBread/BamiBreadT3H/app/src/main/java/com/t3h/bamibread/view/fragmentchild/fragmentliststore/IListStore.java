package com.t3h.bamibread.view.fragmentchild.fragmentliststore;

import com.t3h.bamibread.model.Directions;
import com.t3h.bamibread.model.StoreBamiBread;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public interface IListStore {
    interface ListStoreView{
        void finishgetListStore(List<StoreBamiBread> storeBamiBreads);
        void errorgetListStore(Throwable throwable);

    }
    interface PresenterListStore{
        void getListStoreBami();
    }
}
