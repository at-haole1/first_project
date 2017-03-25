package com.example.kimhao.first_project.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kimhao.first_project.Activity.ListUserFragmentActivity;
import com.example.kimhao.first_project.Fragment.ViewPagerFragment;

/**
 * Created by KimHao on 25/03/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private  int mNumberTab;

    public ViewPagerAdapter(FragmentManager fm, int mNumberTab) {
        super(fm);
        this.mNumberTab = mNumberTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ViewPagerFragment();
            case 1:
                return new ListUserFragmentActivity();
            case 2:
                return new ListUserFragmentActivity();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumberTab;
    }
}
