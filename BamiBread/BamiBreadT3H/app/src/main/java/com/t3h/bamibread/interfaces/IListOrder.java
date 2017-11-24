package com.t3h.bamibread.interfaces;

import com.t3h.bamibread.model.OrderFood;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public interface IListOrder {
    int count();

    OrderFood getOrder(int position);

    void deleteOrderFood(int position);

    void countUpClick(int position);

    void countDownClick(int position);
}
