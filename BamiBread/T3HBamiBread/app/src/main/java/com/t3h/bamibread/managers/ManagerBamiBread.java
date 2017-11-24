package com.t3h.bamibread.managers;

import com.t3h.bamibread.R;
import com.t3h.bamibread.model.BamiBread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class ManagerBamiBread {
    private static ManagerBamiBread managerBamiBread;
    private static ManagerBamiBread managerBami=new ManagerBamiBread ();

    public static ManagerBamiBread  getManagerBami() {
        return managerBami;
    }
    public static ManagerBamiBread getInstance() {
        if ( managerBamiBread== null) {
            managerBamiBread = new ManagerBamiBread();
        }
        return managerBamiBread;
    }

    public List<BamiBread> getListBami(){
        List<BamiBread>locations=new ArrayList<>();
        locations.add(new BamiBread(R.drawable.icon_app,"98 Hàng Bạc, Hà Nội, Việt Nam","0988872930",R.drawable.map2));
        locations.add(new BamiBread(R.drawable.icon_app,"88B Trần Hưng Đạo,Hà Nội,Việt Nam","0988872930",R.drawable.map1));
        locations.add(new BamiBread(R.drawable.icon_app,"128 Giảng Võ, Đống Đa, Hà Nội","0988872930",R.drawable.map2));
        locations.add(new BamiBread(R.drawable.icon_app,"6 Nguyễn Thị Định, Thanh Xuân, Hà Nội","0988872930",R.drawable.map2));
        return locations;
    }
}
