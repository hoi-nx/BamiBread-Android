package com.t3h.bamibread.model;


import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Heart Of Dead on 9/14/2017.
 */

public class OrderFood extends RealmObject{
    @PrimaryKey
    private String name;
    private int price;
    private int number;
    private int totalPrice;
    private String urlImage;


    public OrderFood() {
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public OrderFood(String name, int price, String urlImage, int number) {
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
        this.number = number;
    }

    public OrderFood(String name, int price,int number, int totalPrice, String urlImage) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.totalPrice = totalPrice;
        this.urlImage = urlImage;

    }

    public OrderFood(String name, int price, String urlImage) {
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
    }
}
