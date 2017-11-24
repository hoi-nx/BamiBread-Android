package com.t3h.bamibread.base;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Heart Of Dead on 9/6/2017.
 */

public class BasePresenter<V> {
    private V view;
    private List<Disposable> disposables;

    public V getMyView() {
        return view;
    }

    public BasePresenter(V view) {
        this.view = view;
        disposables = new ArrayList<>();
    }

    public <E> void interact(Observable<E> ob, Action<E> onNext,
                             Action<Throwable> onError) {
        ob = ob.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        checkDis();

        Disposable disposable = ob.subscribe(response -> {
            onNext.call(response);
        }, error -> {
            onError.call(error);
        });
        disposables.add(disposable);


    }

    private void checkDis() {
        for (int i = disposables.size() - 1; i >= 0; i--) {
            if (disposables.get(i).isDisposed()) {
                disposables.remove(i);
            }
        }
    }
}
