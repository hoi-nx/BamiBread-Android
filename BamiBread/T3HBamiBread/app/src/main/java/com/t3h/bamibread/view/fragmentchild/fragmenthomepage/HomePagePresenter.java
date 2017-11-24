package com.t3h.bamibread.view.fragmentchild.fragmenthomepage;

import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.managers.ManagerHomePage;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public class HomePagePresenter extends BasePresenter<IHomePage.HomePageView> implements IHomePage.PresenterHomePage {
    public HomePagePresenter(IHomePage.HomePageView view) {
        super(view);
    }

    @Override
    public void getListImage() {
        interact(ManagerHomePage.getInstance().getListImg(), new Action<List<String>>() {
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
}
