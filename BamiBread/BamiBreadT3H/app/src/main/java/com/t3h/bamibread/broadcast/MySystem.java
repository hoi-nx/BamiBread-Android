package com.t3h.bamibread.broadcast;

import com.t3h.bamibread.base.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 9/21/2017.
 */

public class MySystem {
    private static MySystem mySystem=new MySystem();

    private List<Action<Boolean>>listChangeNetWork;

    public List<Action<Boolean>> getListChangeNetWork() {
        return listChangeNetWork;
    }

    public void setListChangeNetWork(List<Action<Boolean>> listChangeNetWork) {
        this.listChangeNetWork = listChangeNetWork;
    }

    public MySystem(){
        listChangeNetWork=new ArrayList<>();
    }

    public static MySystem getIntance(){
        return mySystem;
    }
    public void makeChangeInternet(boolean isConnect){
        for(Action action: listChangeNetWork){
            action.call(isConnect);
        }
    }
    public void register(Action action){
        listChangeNetWork.add(action);
    }
    public void unregister(Action action){
        listChangeNetWork.remove(action);
    }
}
