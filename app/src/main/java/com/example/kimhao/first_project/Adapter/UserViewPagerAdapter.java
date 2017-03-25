package com.example.kimhao.first_project.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kimhao.first_project.Fragment.ItemUserFragment;
import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.SQLiteData.DataBaseUser;

import java.util.ArrayList;

/**
 * Created by KimHao on 25/03/2017.
 */

public class UserViewPagerAdapter extends FragmentStatePagerAdapter {
    private final Context context;
    private final DataBaseUser mDataBaseUser;
    private ArrayList<ItemUser> mListItemUsers;

    public UserViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        mDataBaseUser = new DataBaseUser(context);
    }

    @Override
    public Fragment getItem(int position) {
        return new ItemUserFragment().newInstance(position);
    }

    @Override
    public int getCount() {
        mListItemUsers =mDataBaseUser.getAllUsers();
        return mListItemUsers.size();
    }
}
