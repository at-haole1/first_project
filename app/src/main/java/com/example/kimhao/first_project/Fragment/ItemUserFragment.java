package com.example.kimhao.first_project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.Activity.UserDetailActivity;
import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SQLiteData.DataBaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KimHao on 23/03/2017.
 */

public class ItemUserFragment extends Fragment {
    private ImageView mImgAva;
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvAddr;
    private ImageView mImgFav;
    private DataBaseUser mDataBaseUser;
    private ItemUser mItemUser;
    private final int REQUEST_CODE = 1;
    private ArrayList<ItemUser> mListItemUsers = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_list_user,null);
        mImgAva = (ImageView) v.findViewById(R.id.imgAva);
        mTvName = (TextView) v.findViewById(R.id.tvName);
        mTvAge = (TextView) v.findViewById(R.id.tvAge);
        mTvAddr = (TextView) v.findViewById(R.id.tvAddr);
        mImgFav = (ImageView) v.findViewById(R.id.imgFavorite);
        mDataBaseUser = new DataBaseUser(getContext());

        int i = getArguments().getInt("position");
        mListItemUsers = mDataBaseUser.getAllUsers();
        mItemUser = mListItemUsers.get(i);

        if (!TextUtils.isEmpty(mItemUser.getImage())){
            Picasso.with(v.getContext())
                    .load(mItemUser.getImage())
                    .fit()
                    .centerCrop()
                    .into(mImgAva);
        }else {
            mImgAva.setImageResource(R.drawable.ic_person);
        }
        mTvName.setText(mItemUser.getName());
        mTvAge.setText(mItemUser.getAge());
        mTvAddr.setText(mItemUser.getAddress());
        mImgFav.setSelected(mItemUser.isFavorite());
        mImgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgFav.setSelected(mItemUser.isFavorite());
                mItemUser.setFavorite(!mItemUser.isFavorite());
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("userclick",mItemUser);
                intent.putExtra("user",bundle);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        return v;
    }

    public ItemUserFragment newInstance(int position){
        ItemUserFragment itemUserFragment = new ItemUserFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        itemUserFragment.setArguments(bundle);
        return itemUserFragment;
    }
}

