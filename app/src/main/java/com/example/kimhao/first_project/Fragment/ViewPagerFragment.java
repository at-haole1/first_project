package com.example.kimhao.first_project.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimhao.first_project.Adapter.UserViewPagerAdapter;
import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SQLiteData.DBHelper;

import java.util.ArrayList;

/**
 * Created by KimHao on 23/03/2017.
 */

public class ViewPagerFragment extends Fragment {
    private DBHelper mDBHelper;
    private ArrayList<ItemUser> mListItemUsers = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_pager_fragment,null);
        ViewPager mViewPager = (ViewPager) v.findViewById(R.id.viewPagerUser);
        UserViewPagerAdapter mUserViewPagerAdapter = new UserViewPagerAdapter(getChildFragmentManager(),v.getContext());
        mViewPager.setAdapter(mUserViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return v;
    }
}
