package com.t3h.bamibread.view.fragmentchild.fragmentliststore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.t3h.bamibread.MapsActivity;
import com.t3h.bamibread.R;
import com.t3h.bamibread.adapters.AdapterListStore;
import com.t3h.bamibread.base.BaseFragment;
import com.t3h.bamibread.interfaces.IListStoreBami;
import com.t3h.bamibread.model.StoreBamiBread;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class FragmentListStore extends BaseFragment implements IListStoreBami, IListStore.ListStoreView {
    @BindView(R.id.rc_list_store)
    RecyclerView mRcListStore;
    private AdapterListStore mAdapterListStore;
    private ListStorePresenter mListStorePresenter;
    private List<StoreBamiBread>bamiBreadList;
    private static final String TAG=FragmentListStore.class.getSimpleName();

    @Override
    public int getLayoutMain() {
        return R.layout.fragment_list_store;
    }

    @Override
    public void initComponents() {
        mListStorePresenter = new ListStorePresenter(this);
        mListStorePresenter.getListStoreBami();


        registerForContextMenu(mRcListStore);

    }

    @Override
    public void addEvents() {

    }


    @Override
    public int count() {
        return bamiBreadList==null?0:bamiBreadList.size();
    }

    @Override
    public Object getData(int position) {
        return bamiBreadList.get(position);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        StoreBamiBread storeBamiBread = bamiBreadList.get(position);
        intent.putExtra("STORE", storeBamiBread);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // getMenuInflater().inflate(R.menu.main_menu,menu);
        getActivity().getMenuInflater().inflate(R.menu.menu_list_store, menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//          // StoreBamiBread storeBamiBread=mListStorePresenter.getListStore().get(adapterContextMenuInfo.position);
//        switch (item.getItemId()){
//            case R.id.idCall:
//                String numberPhone=storeBamiBread.getPhone();
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:"+numberPhone));
//                getContext().startActivity(intent);
//                break;
//        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void finishgetListStore(List<StoreBamiBread> storeBamiBreads) {
        if(storeBamiBreads!=null){
            bamiBreadList=storeBamiBreads;
            mAdapterListStore = new AdapterListStore(this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRcListStore.setLayoutManager(layoutManager);
            mRcListStore.setAdapter(mAdapterListStore);
            mAdapterListStore.notifyDataSetChanged();
        }


    }

    @Override
    public void errorgetListStore(Throwable throwable) {
        Log.d(TAG, "errorgetListStore: "+throwable);

    }
}
