package com.t3h.bamibread.model;

import java.io.Serializable;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class NewsBamiBread implements Serializable{
    private String message;
    private String linkImage;
    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public NewsBamiBread(String message, String linkImage, String cover) {
        this.message = message;
        this.linkImage = linkImage;
        this.cover = cover;
    }

    public NewsBamiBread(String message, String linkImage) {
        this.message = message;
        this.linkImage = linkImage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getMessage() {
        return message;
    }

    public String getLinkImage() {
        return linkImage;
    }
}
