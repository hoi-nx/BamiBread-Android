package com.t3h.bamibread.view.fragmentchild.fragmentintroducestore;

import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;

import com.t3h.bamibread.managers.ManagerIntroduceStore;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/12/2017.
 */

public class IntroduceStorePresenter extends BasePresenter<IIntroduceStore.IntroduceView>implements IIntroduceStore.Presenter {

    public IntroduceStorePresenter(IIntroduceStore.IntroduceView view) {
        super(view);
    }

    @Override
    public void getListImg() {
//        interact(ManagerIntroduceStore.getInstance().getListImg(), new Action<List<String>>() {
//            @Override
//            public void call(List<String> strings) {
//                getMyView().finishGetListImg(strings);
//
//            }
//        }, new Action<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                getMyView().errorGetString(throwable);
//            }
//        });

    }
}
