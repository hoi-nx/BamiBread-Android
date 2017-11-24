package com.t3h.bamibread.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class FastFood{

    private String name;
    private int price;
    private String urlImage;

    public FastFood() {

    }

    public FastFood(String name, int price, String urlImage) {
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
