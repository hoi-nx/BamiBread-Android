package com.t3h.bamibread.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/19/2017.
 */
@IgnoreExtraProperties
public class Request {
    private String name;
    private String phone;
    private String address;
    private String nameStore;

    private List<OrderFood> orderFoods;
    private String status;
    private int total;

    public Request() {
    }

    public Request(List<OrderFood> orderFoods) {
        this.orderFoods = orderFoods;
    }

    public Request(String name, String phone, String address, List<OrderFood> orderFoods, String status, int total) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orderFoods = orderFoods;
        this.status = status;
        this.total = total;
    }

    public Request(String name, String phone, String address, String nameStore, List<OrderFood> orderFoods, String status, int total) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.nameStore = nameStore;
        this.orderFoods = orderFoods;
        this.status = status;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public List<OrderFood> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(List<OrderFood> orderFoods) {
        this.orderFoods = orderFoods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
