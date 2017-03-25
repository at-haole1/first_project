package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.kimhao.first_project.Adapter.UserAdapter;
import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SQLiteData.DataBaseUser;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity implements View.OnClickListener, UserAdapter.MyOnClickListener {

    private ImageView mImgBack;
    private ImageView mImgSettings;
    private RecyclerView mRecyclerViewListUser;
    private UserAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemUser> mUsers;
    private final int REQUEST_CODE = 1;
    private final DataBaseUser mUserDatabase = new DataBaseUser(this);

    private void init() {
        mImgBack = (ImageView) findViewById(R.id.imgBack);
        mImgSettings = (ImageView) findViewById(R.id.imgSettings);
        mRecyclerViewListUser = (RecyclerView) findViewById(R.id.recyclerViewListUser);
        mImgBack.setOnClickListener(this);
        mImgSettings.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        init();
        mRecyclerViewListUser.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewListUser.setLayoutManager(mLayoutManager);
        if (mUserDatabase.getAllUsers().size() == 0) {
            ItemUser user = new ItemUser("content://com.android.providers.media.documents/document/image%3A12", "hao", "22", "male");
            mUserDatabase.insertUser(user);
        } else {
            mUsers = mUserDatabase.getAllUsers();
        }
        mUsers = mUserDatabase.getAllUsers();
        mAdapter = new UserAdapter(this, mUsers, mRecyclerViewListUser, this);
        Log.d("Size mUser", "onCreate: "+mAdapter.toString());
        mRecyclerViewListUser.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgSettings:
                // add user
                Intent intent = new Intent(this, AddEditActivity.class);
                intent.putExtra("value", "add");
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(this, AddEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", mUsers.get(position));
        intent.putExtra("object", bundle);
        intent.putExtra("value", "edit");
        intent.putExtra("index", position);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                boolean isCheck = data.getBooleanExtra("isCheck", false);
                int index = data.getIntExtra("index", -1);
                ItemUser user = mUsers.get(index);
                user.setFavorite(isCheck);
                if (index != -1) {
                    mUsers.set(index, mUsers.get(index));
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
