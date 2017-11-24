package com.t3h.t3hbamibread;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Heart Of Dead on 9/20/2017.
 */
@IgnoreExtraProperties
public class User {
    private String email;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email) {
        this.email = email;
    }
}
