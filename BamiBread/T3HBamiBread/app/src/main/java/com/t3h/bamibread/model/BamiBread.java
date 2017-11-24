package com.t3h.bamibread.model;

/**
 * Created by Heart Of Dead on 9/11/2017.
 */

public class BamiBread {
    private int imgAddress;
    private String address;
    private String phone;
    private String timeWork;
    private String fullAddress;
    private int imgMap;

    public BamiBread(int imgAddress, String address, String phone, String timeWork, String fullAddress) {
        this.imgAddress = imgAddress;
        this.address = address;
        this.phone = phone;
        this.timeWork = timeWork;
        this.fullAddress = fullAddress;
    }

    public BamiBread(int imgAddress, String address, String phone) {
        this.imgAddress = imgAddress;
        this.address = address;
        this.phone = phone;
    }

    public BamiBread(int imgAddress, String address, String phone, int imgMap) {
        this.imgAddress = imgAddress;
        this.address = address;
        this.phone = phone;
        this.imgMap = imgMap;
    }

    public int getImgAddress() {
        return imgAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public int getImgMap() {
        return imgMap;
    }

    public void setImgMap(int imgMap) {
        this.imgMap = imgMap;
    }
}
