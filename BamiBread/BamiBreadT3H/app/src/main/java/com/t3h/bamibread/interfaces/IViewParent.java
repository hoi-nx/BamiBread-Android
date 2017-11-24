package com.t3h.bamibread.interfaces;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public interface IViewParent<T> {
    void finish(T value);

    void error(Throwable error);
}
