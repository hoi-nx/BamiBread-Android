package com.t3h.bamibread.interfaces;

import com.t3h.bamibread.model.FastFood;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public interface IListFastFood {
    int countList();

    FastFood getDataFF(int position);

    Object getImageSecond(int positon);

    void clickOrderFood(int position);
}
