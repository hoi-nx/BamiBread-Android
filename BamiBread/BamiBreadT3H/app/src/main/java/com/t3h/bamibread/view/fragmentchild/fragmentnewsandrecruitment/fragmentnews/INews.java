package com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment.fragmentnews;

import android.content.Context;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public interface INews {
    interface NewsView{
//        void finishGetListImg(List<String> listImg);
//
//        void errorGetString(Throwable error);

    }
    interface PresenterNews{
        void getPostBamiBread(Context context,String keyPage);
    }
}
