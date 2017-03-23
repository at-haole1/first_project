package com.example.kimhao.first_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.kimhao.first_project.Adapter.AdapterRecyclerView;
import com.example.kimhao.first_project.SQLite.DBHelper;
import com.example.kimhao.first_project.model.ItemList;
import com.example.kimhao.first_project.model.ItemUser;

import java.util.ArrayList;
import java.util.List;

public class ListRecyclerViewActivity1 extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    Handler handler;
    RelativeLayout proGress;
    private AdapterRecyclerView mAdapterRecyclerView;
    private static int current_page = 1;

    private int ival = 1;
    private int loadLimit = 20;

    private DBHelper mDBHelper;
    List<ItemList> mListItemUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new Handler();

        initView();
        if (mListItemUsers.size() < 0){
            loadData();
        }

        Log.d("Danh sách mới: ", "onCreate: "+mListItemUsers.size()+"");
    }

    public void initView() {
        mDBHelper = new DBHelper(this);
        mListItemUsers.addAll(mDBHelper.getAllContacts());
        Log.d("Size list: ", "initView: "+mListItemUsers.size()+"");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        proGress = (RelativeLayout) findViewById(R.id.cusPro);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapterRecyclerView = new AdapterRecyclerView(mListItemUsers, ListRecyclerViewActivity1.this, new AdapterRecyclerView.OnBundleTransfer() {

            @Override
            public void onClick(ItemUser itemUser) {
                Intent i = new Intent(ListRecyclerViewActivity1.this, DetailRecyclerViewActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Data", (Parcelable) itemUser);
                Log.d("toi muon biet", "onClick: " + itemUser.getName());
                i.putExtra("intent", bundle);
                startActivityForResult(i, 1);

            }
        });
        mRecyclerView.setAdapter(mAdapterRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemAdd:
                Intent intent =  new Intent(getApplicationContext(), DetailRecyclerViewActivity2.class);
                intent.putExtra("key","AddContact");
                startActivity(intent);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
            }
        }
        // mAdapterRecyclerView
//        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
//            @Override
//            public void onLoadMore(final int current_page) {
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadMoreData(current_page);
//                    }
//                },1000);
//
//            }
//        });

//        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int lastVisibbleItem = layoutManager.findLastCompletelyVisibleItemPosition();
//                if (lastVisibbleItem == mListItemUsers.size()-1){
//                    proGress.setVisibility(View.VISIBLE);
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d("lodaing.........", "run: ");
//                            int nextItem = mListItemUsers.size();
//
//                            loadMoreData();
//                            mAdapterRecyclerView.notifyItemInserted(mListItemUsers.size());
//                            proGress.setVisibility(View.GONE);
//                        }
//                    },2000);
//
//                }
//            }
//        });




    private void loadData() {

        for (int i = ival; i < loadLimit; i++) {
            if (i%2==0){
                mListItemUsers.add(new ItemUser(R.drawable.ic_person+"","User "+i+"",""+i,
                        "Đà Nẵng"));
                Log.d("Load ra danh sach nao: ", "loadData: "+R.drawable.ic_person+"");
            }else {
                mListItemUsers.add(new ItemUser(R.drawable.ic_user + "", "User " + i + "", "" + i,
                        "Đà Nẵng"));
            }
            ival++;
        }
        mAdapterRecyclerView.notifyDataSetChanged();
    }

    private void loadMoreData() {
        loadLimit = ival + 20;
        for (int i = ival; i < loadLimit; i++) {
            if (i % 2 == 0){
                mListItemUsers.add(new ItemUser(R.drawable.ic_person+"","User "+i+"",""+i,
                        "Đà Nẵng"));
            }else {
                mListItemUsers.add(new ItemUser(R.drawable.ic_user + "", "User " + i + "", "" + i,
                        "Đà Nẵng"));
            }
            ival++;
        }
        mAdapterRecyclerView.notifyDataSetChanged();
    }


}
