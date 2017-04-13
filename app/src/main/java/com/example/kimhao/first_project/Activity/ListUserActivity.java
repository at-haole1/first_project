package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.kimhao.first_project.Adapter.UserAdapter;
import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SQLiteData.DataBaseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
@EActivity(R.layout.activity_list_user)
public class ListUserActivity extends AppCompatActivity implements UserAdapter.MyOnClickListener {

    @ViewById(R.id.imgBack)
    ImageView mImgBack;

    @ViewById(R.id.imgAddUser)
    ImageView mImgSettings;

    @ViewById(R.id.recyclerViewListUser)
    RecyclerView mRecyclerViewListUser;

    private UserAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemUser> mUsers;
    String Add = "add";
    private final int REQUEST_CODE = 1;
    private final DataBaseUser mUserDatabase = new DataBaseUser(this);


    @Click(R.id.imgBack)
    void clickBack(){
        finish();
    }

    @Click(R.id.imgAddUser)
    void clickAdd(){
        // add user
        AddEditActivity_.intent(ListUserActivity.this).mValue(Add).start();
        Log.e("aaaa", "clickAdd: ");
//        Intent intent = new Intent(this, AddEditActivity.class);
//        intent.putExtra("value", "add");
//        startActivity(intent);
    }

    @AfterViews
    void create() {
        mRecyclerViewListUser.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewListUser.setLayoutManager(mLayoutManager);
        if (mUserDatabase.getAllUsers().size() == 0) {
            ItemUser user = new ItemUser("content://com.android.providers.media.documents/document/image%3A185", "hao", "22", "male");
            mUserDatabase.insertUser(user);
        } else {
            mUsers = mUserDatabase.getAllUsers();
        }
        mUsers = mUserDatabase.getAllUsers();
        mAdapter = new UserAdapter(this, mUsers, mRecyclerViewListUser, this);
        Log.d("Size mUser", "onCreate: "+mUsers.size());
        mRecyclerViewListUser.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickListener(int position) {
        AddEditActivity_.intent(getBaseContext()).mPosition(position).mUser(mUsers.get(position)).mValue("aa").startForResult(4);

//        Intent intent = new Intent(this, AddEditActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("data", mUsers.get(position));
//        intent.putExtra("object", bundle);
//        intent.putExtra("value", "edit");
//        intent.putExtra("index", position);
//        startActivity(intent);
//        finish();
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
