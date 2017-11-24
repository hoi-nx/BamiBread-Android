package com.t3h.t3hbamibread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeUser();
    }

    private void writeUser(){

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        User user=new User("xuanhoi@gmail.com");
        databaseReference.child("users").child(String.valueOf(System.currentTimeMillis())).setValue(user);


    }
}
