package com.t3h.bamibread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Heart Of Dead on 9/19/2017.
 */

public class FragmentOderShip extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView rc;
    private FirebaseRecyclerAdapter<Request, MyViewHolderRc> recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.oder_food_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = mDatabase.child("Requests");
        rc = getView().findViewById(R.id.rc_);


        recyclerAdapter = new FirebaseRecyclerAdapter<Request, MyViewHolderRc>(Request.class,R.layout.item_rc_order,MyViewHolderRc.class,query) {
            @Override
            protected void populateViewHolder(MyViewHolderRc viewHolder, Request model, int position) {
                viewHolder.txtName.setText(model.getName());
                viewHolder.txtPhone.setText(model.getPhone()+"");
                viewHolder.txtAddress.setText(model.getAddress()+"");
                viewHolder.txtTotal.setText(model.getTotal()+"");

            }
        };
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference ref = database.child("Requests");
//
//        Query phoneQuery = ref.orderByChild("").equalTo("88B Trần Hưng Đạo, Hà Nội");
//        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
//                   ;
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get Post object and use the values to update the UI
                Log.d("demo", "onDataChange: " + dataSnapshot.getValue(Request.class).getAddress());


                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addListenerForSingleValueEvent(postListener);
        //mDatabase.addValueEventListener(postListener);
    }
}
