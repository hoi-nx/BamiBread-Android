package com.t3h.bamibread.view.fragmentchild.fragmentnewsandrecruitment.fragmentnews;

import android.content.Context;

import com.t3h.bamibread.base.Action;
import com.t3h.bamibread.base.BasePresenter;
import com.t3h.bamibread.interfaces.IViewParent;
import com.t3h.bamibread.managers.ManagerNews;
import com.t3h.bamibread.model.NewsBamiBread;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class NewsPresenter extends BasePresenter<IViewParent>implements INews.PresenterNews {


    public NewsPresenter(IViewParent view) {
        super(view);
    }

    @Override
    public void getPostBamiBread(Context context,String keyPage) {
       interact(ManagerNews.getIntance().getPostBami(context,keyPage), new Action<List<NewsBamiBread>>() {
           @Override
           public void call(List<NewsBamiBread> newsBamiBreads) {
               getMyView().finish(newsBamiBreads);

           }
       }, new Action<Throwable>() {
           @Override
           public void call(Throwable throwable) {
               getMyView().error(throwable);

           }
       });
    }
}
