package com.example.kimhao.first_project.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by KimHao on 23/03/2017.
 */

public class MyGragPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> pages = new ArrayList<>();

    public MyGragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public void addPage(Fragment f){
        pages.add(f);
    }

    //set title for tab
    @Override
    public CharSequence getPageTitle(int position){
        return pages.get(position).toString();
    }

}
