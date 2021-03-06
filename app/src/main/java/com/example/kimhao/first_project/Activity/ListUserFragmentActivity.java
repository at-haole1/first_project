package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kimhao.first_project.Adapter.UserAdapter;
import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SQLiteData.DataBaseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by KimHao on 25/03/2017.
 */
@EFragment(R.layout.activity_list_user)
public class ListUserFragmentActivity extends Fragment implements UserAdapter.MyOnClickListener {
    private UserAdapter mUserAdapter;
    private ArrayList<ItemUser> mListUser;
    private final int REQUES_CODE = 411;

    @ViewById(R.id.recyclerViewListUser)
    RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseUser mDataBaseUser = new DataBaseUser(getActivity());
        if(mDataBaseUser.getAllUsers().size() == 0){
            ItemUser user = new ItemUser("content://com.android.providers.media.documents/document/image%3A12", "hao", "22", "male");
            mDataBaseUser.insertUser(user);
        }else {
            mListUser = mDataBaseUser.getAllUsers();
        }
    }

    @AfterViews
    void afterview(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        mUserAdapter = new UserAdapter(getContext(),mListUser,recyclerView,this);
        recyclerView.setAdapter(mUserAdapter);
        mUserAdapter.notifyDataSetChanged();
    }
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.activity_list_user,container,false);
//        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewListUser);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        mUserAdapter = new UserAdapter(v.getContext(),mListUser,recyclerView,this);
//        recyclerView.setAdapter(mUserAdapter);
//        mUserAdapter.notifyDataSetChanged();
//        return v;
//    }

    @Override
    public void onClickListener(int position) {

        ItemUser user = mListUser.get(position);
//        Intent intent = new Intent(getActivity(),UserDetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("userclick",user);
//        intent.putExtra("user",bundle);
//        intent.putExtra("Pos",position);
//        Log.d("position", "onClickListener: "+position);
        UserDetailActivity_.intent(getContext()).mPos(position).mUser(user).startForResult(REQUES_CODE);
//        startActivityForResult(intent,REQUES_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUES_CODE && resultCode == RESULT_OK && null != data){
            boolean check = data.getBooleanExtra("check",false);
            int pos = data.getIntExtra("Pos",-1);
            Log.d("gggggggggggggggggggg", "onActivityResult: "+pos);
            ItemUser user = mListUser.get(pos);
            user.setFavorite(check);
            if (pos != -1){
                mListUser.set(pos,mListUser.get(pos));
                mUserAdapter.notifyDataSetChanged();
            }

        }
    }
}
