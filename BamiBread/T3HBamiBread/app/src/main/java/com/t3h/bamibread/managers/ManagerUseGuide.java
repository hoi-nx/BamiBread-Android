package com.t3h.bamibread.managers;

import com.t3h.bamibread.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 9/10/2017.
 */

public class ManagerUseGuide {
    private static ManagerUseGuide managerUseGuide=new ManagerUseGuide();
    private List<Integer>list;

    public List<Integer> getList() {
        return list;
    }

    public static ManagerUseGuide getInstance(){
        return managerUseGuide;
    }
    public List<Integer>getImageUseGuide(){
       list = new ArrayList<>();
        list.add(R.drawable.sh_genre);
        list.add(R.drawable.sh_home);
        list.add(R.drawable.sh_player);
        return list;
    }
}
