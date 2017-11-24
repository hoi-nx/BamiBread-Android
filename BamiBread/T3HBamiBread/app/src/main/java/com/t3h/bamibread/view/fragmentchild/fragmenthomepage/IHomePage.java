package com.t3h.bamibread.view.fragmentchild.fragmenthomepage;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/13/2017.
 */

public interface IHomePage {
    interface HomePageView{
        void finishGetListImg(List<String> listImg);

        void errorGetString(Throwable error);

    }
    interface PresenterHomePage{
        void getListImage();
    }
}
