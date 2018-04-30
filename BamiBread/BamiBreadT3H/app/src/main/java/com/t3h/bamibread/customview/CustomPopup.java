package com.t3h.bamibread.customview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterSpinner;
import com.t3h.bamibread.interfaces.IList;
import com.t3h.bamibread.interfaces.IPopup;
import com.t3h.bamibread.interfaces.IViewMain;
import com.t3h.bamibread.managers.ManagerListStore;
import com.t3h.bamibread.managers.ManagerRealm;
import com.t3h.bamibread.model.OrderFood;
import com.t3h.bamibread.model.Request;
import com.t3h.bamibread.model.StoreBamiBread;
import com.t3h.bamibread.view.fragmentchild.fragmentliststore.IListStore;
import com.t3h.bamibread.view.fragmentchild.fragmentliststore.ListStorePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class CustomPopup extends Dialog implements IList, IViewMain, AdapterView.OnItemSelectedListener, IListStore.ListStoreView {


    private Spinner mSpinner;
    private AdapterSpinner mAdapterSpinner;
    private static final String TAG = CustomPopup.class.getSimpleName();
    private DatabaseReference databaseReference;
    private RealmResults<OrderFood> realmResults;
    private Realm realm;
    private TextView edtName, edtPhone, edtAddress, edtStatus;
    private StoreBamiBread storeBamiBread;

    private IPopup iPopup;
    private ListStorePresenter listStorePresenter;
    private List<StoreBamiBread>storeBamiBreadList;


    public CustomPopup(@NonNull Context context, IPopup iPopup) {
        super(context);
        this.iPopup = iPopup;

        initDialog();

    }

    private void initDialog() {
        setContentView(getLayoutMain());
        setCanceledOnTouchOutside(false);
        initComponents();
        addEvents();
    }

    @Override
    public int count() {
        return storeBamiBreadList==null?0:storeBamiBreadList.size();
    }

    @Override
    public Object getData(int position) {
        return storeBamiBreadList.get(position);
    }

    @Override
    public int getLayoutMain() {
        return R.layout.popup_order_ship;
    }

    @Override
    public void initComponents() {
        realm = Realm.getDefaultInstance();
        realmResults = ManagerRealm.getIntance().getListOrderFood(realm);
        mSpinner = findViewById(R.id.spinner_choose_store);

        listStorePresenter=new ListStorePresenter(this);
        listStorePresenter.getListStoreBami();

        edtName = findViewById(R.id.name_ship);
        edtPhone = findViewById(R.id.phone_ship);
        edtAddress = findViewById(R.id.address_ship);
        edtStatus = findViewById(R.id.status_ship);


    }

    @Override
    public void addEvents() {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd/MM/yyyy");
        String time=simpleDateFormat1.format(date);
        Log.d(TAG, "addEvents: "+time);
        mSpinner.setOnItemSelectedListener(this);
        findViewById(R.id.btnNo).setOnClickListener(view -> {
            dismiss();
        });
        findViewById(R.id.btnYes).setOnClickListener(view -> {
                databaseReference = FirebaseDatabase.getInstance().getReference();
            if (edtAddress.getText().toString().equals("") || edtPhone.getText().toString().equals("") || edtAddress.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_LONG).show();
                return;
            }
            Request mRequest = new Request(edtName.getText().toString(), edtPhone.getText().toString(), edtAddress.getText().toString(), realmResults, edtStatus.getText().toString(), ManagerRealm.getIntance().totalPriceListOrder(realmResults));
            databaseReference.child("Requests").child(storeBamiBread.getNameStore()).child(String.valueOf(System.currentTimeMillis())).setValue(mRequest);
            dismiss();


            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Get Post object and use the values to update the UI
                    Log.d("demo", "onDataChange: "+dataSnapshot.getValue(Request.class).getAddress().toString());

                    // ...
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w("", "loadPost:onCancelled", databaseError.toException());
                    // ...
                }
            };
            databaseReference.addListenerForSingleValueEvent(postListener);

            iPopup.clearAllListOrder();

        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(), "==" + i, Toast.LENGTH_LONG).show();
        storeBamiBread = storeBamiBreadList.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void finishgetListStore(List<StoreBamiBread> storeBamiBreads) {
        if(storeBamiBreads!=null){
            storeBamiBreadList=storeBamiBreads;
            mAdapterSpinner = new AdapterSpinner(this);
            mSpinner.setAdapter(mAdapterSpinner);
            mAdapterSpinner.notifyDataSetChanged();
        }

    }

    @Override
    public void errorgetListStore(Throwable throwable) {
        Log.d(TAG, "errorgetListStore: "+throwable);

    }
}
