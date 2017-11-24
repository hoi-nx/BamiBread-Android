package com.t3h.bamibread.model;

import java.io.Serializable;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class StoreBamiBread implements Serializable {
    private String nameStore;
    private String phone;
    private int imgStore;
    private String linkImg;

    public String getLinkImg() {
        return linkImg;
    }

    public StoreBamiBread(String nameStore, String phone, String linkImg) {
        this.nameStore = nameStore;
        this.phone = phone;
        this.linkImg = linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public StoreBamiBread(String nameStore, String phone, int imgStore) {
        this.nameStore = nameStore;
        this.phone = phone;
        this.imgStore = imgStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImgStore() {
        return imgStore;
    }

    public void setImgStore(int imgStore) {
        this.imgStore = imgStore;
    }
}
