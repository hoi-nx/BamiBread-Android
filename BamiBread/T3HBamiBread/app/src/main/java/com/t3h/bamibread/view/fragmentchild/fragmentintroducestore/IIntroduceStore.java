package com.t3h.bamibread.view.fragmentchild.fragmentintroducestore;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/12/2017.
 */

public interface IIntroduceStore {
    interface IntroduceView{
        void finishGetListImg(List<String> listImg);

        void errorGetString(Throwable error);


    }
    interface Presenter{
        void getListImg();
    }
}
